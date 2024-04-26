package com.DbLayer.DbLayer.controller;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.apache.logging.log4j.util.PropertySource.Comparator;
// import org.hibernate.engine.internal.Collections;
import com.DbLayer.DbLayer.entity.CalculatedVales;
import com.DbLayer.DbLayer.entity.MFEntity;
import com.DbLayer.DbLayer.entity.ReturnsCalculate;
import com.DbLayer.DbLayer.model.Input;
import com.DbLayer.DbLayer.repo.MFrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.webencyclop.core.mftool.MFTool;
import com.webencyclop.core.mftool.models.Data;
import com.webencyclop.core.mftool.models.output.SchemeNameCodePair;

@RestController
public class MfTool {

    @Autowired
    private MFTool mftool;

    @Autowired
    private MFrepository mFrepository;

    private Map<String, Input> dictionary = new HashMap<>();




    



    @GetMapping("/")
    public ResponseEntity<List<MFEntity>> fetchListOfAllMutualFundSchemes(@RequestParam List<String> list) {
        return new ResponseEntity<>(mFrepository.findByCodeIn(list),HttpStatus.CREATED);
    }




    // @GetMapping("/mf/{name}")
    // public String AllMfByName(@PathVariable("name") String name ,Model model) throws IOException {
    //     List<SchemeNameCodePair> input = mftool.matchingScheme(name); 
    //     // This will fetch MF schemes that have "Axis" in the name.
    //     dictionary.clear();
    //     Map<String,Input> map=new HashMap<>();
    //     int count=0;
    //     for(SchemeNameCodePair i : input){
    //         BigDecimal nav = mftool.getCurrentNav(i.getCode());
    //         Double per3=getReturnPercent(3, i.getCode());
    //         Double per1=getReturnPercent(1, i.getCode());
    //         if(per1!=0 && per3!=0){
    //             map.put(String.valueOf(i.getName()),new Input(String.valueOf(nav),String.valueOf(i.getCode()),per1,per3));
    //         }
    //         count++;
    //         if(count==50){break;}
    //     }
    //     String path="/images/img.png";
    //     dictionary.putAll(map);
    //     switch(name){
    //         case "Axis" -> {path="/images/Axis.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}
            
    //         case "SBI" -> {path="/images/SBI.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Groww" -> {path="/images/Groww.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Zerodha" -> {path="/images/Zerodha.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Nippon" -> {path="/images/Nippon.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Kotak" -> {path="/images/Kotak.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "HDFC" -> {path="/images/HDFC.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Edelweiss" -> {path="/images/Edelweiss.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "ICICI" -> {path="/images/ICICI.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Franklin" -> {path="/images/Franklin.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

            
    //         case "Quant" -> {path="/images/Quant.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         case "Prag" -> {path="/images/Prag.png";
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";}

    //         default -> {
    //             model.addAttribute("dict", dictionary);
    //             model.addAttribute("path", path);
    //             model.addAttribute("name", name);
    //             return "mf";
    //         }

            
    //     }
        

    // }








    @GetMapping("/mfbyname")
    public ResponseEntity<List<MFEntity>> AllMfByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(mFrepository.findByShortName(name),HttpStatus.CREATED);

    }








    public Double getReturnPercent(int n, String code) throws IOException{
        List<Data> list = mftool.historicNavForScheme(code);
        
        Map<Integer,Integer> map=new HashMap<>();

        for(Data i : list){
            if(map.getOrDefault(i.getDate().getYear(),0)!=0){
                continue;
            }
            map.put(i.getDate().getYear(),i.getNav().intValue());
        }

        int first=List.copyOf(map.values()).get(0);
        double per=0;
        List<Double> retValuePercentList=new ArrayList<>();
        List<Integer> year=new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);
        
        int iter=0;
        for(int i :map.values()){
            if(iter==0){iter++; continue;}
            per=((double)(i-first)/first)*100;
            
            retValuePercentList.add(per);
            
            year.add(iter);
            iter++;
            // plotReturn.put(, null)
            
        }
        try{
            return retValuePercentList.get(n);
        }catch(Exception e){
            return 0d;
        }
        

    }



    @GetMapping("/mfNavOnDate")
    public ResponseEntity<CalculatedVales> getNavOnDate(@RequestParam("id") String code) throws IOException {
        List<Data> list = mftool.historicNavForScheme(code);



        Map<Integer,Integer> map=new HashMap<>();

        for(Data i : list){
            if(map.getOrDefault(i.getDate().getYear(),0)!=0){
                continue;
            }
            map.put(i.getDate().getYear(),i.getNav().intValue());
        }

        int first=List.copyOf(map.values()).get(0);
        double per=0;
        List<Double> retValuePercentList=new ArrayList<>();
        List<Integer> year=new ArrayList<>();
        
        int iter=0;
        for(int i :map.values()){
            if(iter==0){iter++; continue;}
            per=((double)(i-first)/first)*100;
            retValuePercentList.add(per);
            
            year.add(iter);
            iter++;
            // plotReturn.put(, null)
            
        }
        



        CalculatedVales calculatedVales=new CalculatedVales();
        calculatedVales.setKeys(new ArrayList<>(map.keySet()));

        System.out.println(calculatedVales.getKeys());
        List<Integer> integerList = new ArrayList<>();
        for(Integer i : map.values()){
            integerList.add(i);
        }
        System.out.println(integerList);

        calculatedVales.setValues(integerList);

        System.out.println(calculatedVales.getValues());
        calculatedVales.setPercentage(retValuePercentList);
        System.out.println(calculatedVales.getPercentage());
        calculatedVales.setYear(year);

        System.out.println("--------------------------------------");
        System.out.println(calculatedVales);
  
		return new ResponseEntity<>(calculatedVales,HttpStatus.CREATED);

    }



    @PostMapping("/sort")
    public ResponseEntity<List<MFEntity>> sort( @RequestParam("sortby") String sortBy,
                        @RequestParam("name")String name)throws IOException {

        if(sortBy.equals("Direct-plan")){
            return new ResponseEntity<>(mFrepository.findByNameContainingAndShortNameContaining("Direct",name),HttpStatus.CREATED);

        }

        if(sortBy.equals("Regular-plan")){
            return new ResponseEntity<>(mFrepository.findByNameContainingAndShortNameContaining("Regular",name) ,HttpStatus.CREATED);

        }

        if(sortBy.equals("percentage-Return-3yr")){

            return new ResponseEntity<>(mFrepository.findByShortNameOrderByRet3Desc(name) ,HttpStatus.CREATED);
        }

        if(sortBy.equals("percentage-Return-1yr")){
            return new ResponseEntity<>(mFrepository.findByShortNameOrderByRet1Desc(name) ,HttpStatus.CREATED);
        }


        if(sortBy.equals("current-nav")){
            return new ResponseEntity<>(mFrepository.findByShortNameOrderByNavDesc(name) ,HttpStatus.CREATED);
        }

 return new ResponseEntity<>(mFrepository.findByShortNameOrderByRet3Desc(name) ,HttpStatus.CREATED);


    }

//
//





    @PostMapping("/calculate")
    public ResponseEntity<ReturnsCalculate> calculate(@RequestParam("name") String name,@RequestParam("total_investment") String total_investment,
                            @RequestParam("Expected_Return_Rate") String Expected_Return_Rate,
                            @RequestParam(value="Time_Period" ,defaultValue="5") String Time_Period
                            ) throws Exception{


        Double r=Double.parseDouble(Expected_Return_Rate)/100;
        Double futureValue = Integer.parseInt(total_investment)*(Math.pow((1+r),Integer.parseInt(Time_Period)));
        Double increasedAmount=futureValue-Integer.parseInt(total_investment);



        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);


        ReturnsCalculate returnsCalculate=new ReturnsCalculate();
        returnsCalculate.setFutureValue(df.format(futureValue));
        returnsCalculate.setList(mFrepository.findByShortName(name));
        returnsCalculate.setIncreasedAmount( df.format(increasedAmount));



        return new ResponseEntity<>(returnsCalculate,HttpStatus.CREATED);




    }
//
//
//
//
//
//
//
//
//
//

    @GetMapping("/database")
    public String database(Model model) throws IOException{
        List<SchemeNameCodePair> inputList = mftool.allSchemes();
        String[] mf={"ICICI","SBI","Groww","Nippon","Franklin","Axis","Zerodha","HDFC","Kotak","UTI","Quant","Prag"};
        Map<String,Integer> check=new HashMap<>();
        String path;
        mFrepository.deleteAll();
        for(SchemeNameCodePair str : inputList){

            for(String i : mf){

                if(check.getOrDefault(i,0)>60){
                    continue;
                }
                if(str.getName().contains(i)){
                    // outputList.add(str.getName());
                    MFEntity object=new MFEntity();
                    object.setName(str.getName());
                    path= "/images/" + str.getName().split(" ")[0] + ".png";
                    object.setPath(path);
                    object.setCode(str.getCode());
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setMaximumFractionDigits(2);

                    object.setRet1( Double.parseDouble(df.format( getReturnPercent(1,str.getCode()))  ) );
                    object.setRet3( Double.parseDouble(df.format( getReturnPercent(3,str.getCode()))));
                    object.setNav(mftool.getCurrentNav(str.getCode()).doubleValue());
                    object.setShortName(i);
                    mFrepository.save(object);
                    check.put(i, check.getOrDefault(i,0)+1);
                    break;
                }

            }

        }
        model.addAttribute("msg", mFrepository.findAll());

    return "temp";

    }

//
//
//
//
//
//
//




//     @GetMapping("/all")
//     public String all(Model model) throws IOException{
//         List<MFEntity> all=mFrepository.findAll();

// for(MFEntity k : all){

//         List<Data> list = mftool.historicNavForScheme(k.getCode());



//         Map<Integer,Integer> map=new HashMap<>();

//         for(Data i : list){
//             if(map.getOrDefault(i.getDate().getYear(),0)!=0){
//                 continue;
//             }
//             map.put(i.getDate().getYear(),i.getNav().intValue());
//         }

//         int first=List.copyOf(map.values()).get(0);
//         double per=0;
//         List<Double> retValuePercentList=new ArrayList<>();
//         List<Integer> year=new ArrayList<>();
        
//         int iter=0;
//         for(int i :map.values()){
//             if(iter==0){iter++; continue;}
//             per=((double)(i-first)/first)*100;
//             retValuePercentList.add(per);
            
//             year.add(iter);
//             iter++;


//             PlotEntity object=new PlotEntity();
//             List<Integer> yrMapList=new ArrayList<>(map.keySet());
            
//             // plotReturn.put(, null)


//             		// model.addAttribute("keySet", map.keySet());
// 		// model.addAttribute("values", map.values());
//         // model.addAttribute("year", year);
// 		// model.addAttribute("per", retValuePercentList);
            
//         }
//     }
        


    // }






}








