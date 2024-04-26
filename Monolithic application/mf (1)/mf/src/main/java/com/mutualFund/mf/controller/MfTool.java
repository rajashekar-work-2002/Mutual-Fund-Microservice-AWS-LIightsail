package com.mutualFund.mf.controller;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.apache.logging.log4j.util.PropertySource.Comparator;
// import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutualFund.mf.entity.MFEntity;
import com.mutualFund.mf.model.Input;
import com.mutualFund.mf.repo.MFrepository;
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




    





    // @GetMapping("/")
    // public String getAllMf(Model model) throws IOException{
    //     // MFTool tool = new MFTool();
    //     List<SchemeNameCodePair> mflist=new ArrayList<>();
    //     mflist  = mftool.matchingScheme("Axis");
    //     model.addAttribute("mflist", mflist);
    //     return "home";
    // }

    // get historical nav
    // @GetMapping("/{str}")
    // public String getCurrentNav(@PathVariable("str") String scheme, Model model) throws IOException {
    //     // BigDecimal nav = mftool.getCurrentNav(scheme);
    //     List<Data> nav = mftool.historicNavForScheme(scheme);
    //     model.addAttribute("nav", nav);
    //     return "nav";
    // }



    // @GetMapping("/")
    // public String fetchListOfAllMutualFundSchemes(Model model) throws IOException {
    //     List<SchemeNameCodePair> inputList = mftool.allSchemes();
    //     // Convert the list of SchemeNameCodePair to List
    //     List<String> outputList=new ArrayList<>();
    //     Map<String,String>map=new HashMap<>();

    //     String[] mf={"ICICI","SBI","Groww","Nippon","Franklin","Axis","Zerodha","HDFC","Kotak","UTI","Quant","Prag"};

    //     Map<String,Integer> check=new HashMap<>();
    //     String path;
    //     for(SchemeNameCodePair str : inputList){

            
    //         for(String i : mf){

    //             if(check.getOrDefault(i,0)>2){
    //                 continue;
    //             }
    //             if(str.getName().contains(i)){
    //                 outputList.add(str.getName());
    //                 path= "/images/" + str.getName().split(" ")[0] + ".png";
    //                 map.put(str.getName(), path);
    //                 check.put(i, check.getOrDefault(i,0)+1);
    //             }

    //         }     

    //     }
        


    //     // model.addAttribute("list", outputList);
    //     model.addAttribute("list", map);

    //     return "home";
    // }


    @GetMapping("/")
    public String fetchListOfAllMutualFundSchemes(Model model) {

        String[] mf={"149928", "118587","118556","112086","103191","119822","152156","121546","141808","100482"};
        List<String> list=new ArrayList<>();
        list=Arrays.asList(mf);

        model.addAttribute("list", mFrepository.findByCodeIn(list));
        return "home";
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








    @GetMapping("/mf/{name}")
    public String AllMfByName(@PathVariable("name") String name ,Model model) {

        model.addAttribute("list",mFrepository.findByShortName(name));
        model.addAttribute("name",name);
        return "mf";

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



    @GetMapping("/mf/code/{id}")
    public String getNavOnDate(@PathVariable("id") String code,Model model) throws IOException {
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
        

		model.addAttribute("keySet", map.keySet());
		model.addAttribute("values", map.values());
        model.addAttribute("year", year);
		model.addAttribute("per", retValuePercentList);
  
		return "barchart";

    }


    // @PostMapping("/sort/{sortby}")
    // public String sort(@RequestParam("value") String inputMapString, @PathVariable("sortby") String sortBy, Model model)throws IOException {
    //     try{
    //     if(sortBy.equals("Direct-plan")){
    //         List<String>mapList=new ArrayList<>(dictionary.keySet());
    //         List<String> filteredList = mapList.stream()
    //                                     .filter(s -> s.contains(" Direct"))
    //                                     .collect(Collectors.toList());
    //         String[] word=filteredList.get(0).split(" ");
    //         String path="/images/" + word[0] + ".png";
    //         Map<String,Input> newMap=new HashMap<>();
    //         for(String i:filteredList){
    //             if(dictionary.containsKey(i)){
    //                 newMap.put(i, dictionary.get(i));
    //             }
    //         }

    //         model.addAttribute("dict", newMap);
    //         model.addAttribute("path", path);
    //         model.addAttribute("name", word[0]);
    //         return "mf";

    //     }

    //     if(sortBy.equals("Regular-plan")){
    //         List<String>mapList=new ArrayList<>(dictionary.keySet());
    //         List<String> filteredList = mapList.stream()
    //                                     .filter(s -> s.contains(" Regular"))
    //                                     .collect(Collectors.toList());
    //         String[] word=filteredList.get(0).split(" ");
    //         String path="/images/" + word[0] + ".png";
    //         Map<String,Input> newMap=new HashMap<>();
    //         for(String i:filteredList){
    //             if(dictionary.containsKey(i)){
    //                 newMap.put(i, dictionary.get(i));
    //             }
    //         }

    //         model.addAttribute("dict", newMap);
    //         model.addAttribute("path", path);
    //         model.addAttribute("name", word[0]);
    //         return "mf";

    //     }

    //     if(sortBy.equals("percentage-Return-3yr")){
    //         List<Input> list=new ArrayList<>(dictionary.values());
    //         Collections.sort(list, Comparator.comparing(Input::getPer3));
    //         Map<String,Input> map=new HashMap<>();

    //         String path="";
    //         String mf="";
    //         int count=0;
    //         for(String str : dictionary.keySet()){
    //             if(count==0){
    //                 mf=str.split(" ")[0];
    //                 path="/images/" + mf + ".png";
    //                 break;
    //             }
                    
    //         }

    //         for(Input j : list){
    //             for(Map.Entry<String,Input> i :dictionary.entrySet()){
    //                 if(i.getValue().getCode().equals(j.getCode())){
    //                     map.put(i.getKey(), i.getValue());
    //                     break;
    //                 }
    //             }
    //         }
    //         model.addAttribute("dict", map);
    //         model.addAttribute("path", path);
    //         model.addAttribute("name", mf);
    //         return "mf";
    //     }

    //     if(sortBy.equals("percentage-Return-1yr")){
    //         List<Input> list=new ArrayList<>(dictionary.values());
    //         Collections.sort(list, Comparator.comparing(Input::getPer1));
    //         Map<String,Input> map=new HashMap<>();

    //         String path="";
    //         String mf="";
    //         int count=0;
    //         for(String str : dictionary.keySet()){
    //             if(count==0){
    //                 mf=str.split(" ")[0];
    //                 path="/images/" + mf + ".png";
    //                 break;
    //             }
    //         }

    //         for(Input j : list){
    //             for(Map.Entry<String,Input> i :dictionary.entrySet()){
    //                 if(i.getValue().getCode().equals(j.getCode())){
    //                     map.put(i.getKey(), i.getValue());
    //                     break;
    //                 }
    //             }
    //         }
    //         model.addAttribute("dict", map);
    //         model.addAttribute("path", path);
    //         model.addAttribute("name", mf);
    //         return "mf";
    //     }


    //     if(sortBy.equals("current-nav")){
    //         List<Input> list=new ArrayList<>(dictionary.values());
    //         Collections.sort(list, Comparator.comparing(Input::getNav));
    //         Map<String,Input> map=new HashMap<>();
    //         String path="";
    //         String mf="";
    //         int count=0;
    //         for(String str : dictionary.keySet()){
    //             if(count==0){
    //                 mf=str.split(" ")[0];
    //                 path="/images/" + mf + ".png";
    //                 break;
    //             }
    //         }

    //         for(Input j : list){
    //             for(Map.Entry<String,Input> i :dictionary.entrySet()){
    //                 if(i.getValue().getCode().equals(j.getCode())){
    //                     map.put(i.getKey(), i.getValue());
    //                     break;
    //                 }
    //             }
    //         }
    //         model.addAttribute("dict", map);
    //         model.addAttribute("path", path);
    //         model.addAttribute("name", mf);
    //         return "mf";
    //     }

    //     return "mf";

    // }catch(Exception e){
    //     return "redirect:/";
    // }

    // }



    @PostMapping("/sort/{sortby}/{name}")
    public String sort( @PathVariable("sortby") String sortBy,
                        @PathVariable("name")String name, Model model)throws IOException {

        if(sortBy.equals("Direct-plan")){
            model.addAttribute("list",mFrepository.findByNameContainingAndShortNameContaining("Direct",name));
            model.addAttribute("name",name);
            return "mf";

        }

        if(sortBy.equals("Regular-plan")){
            model.addAttribute("list",mFrepository.findByNameContainingAndShortNameContaining("Regular",name));
            model.addAttribute("name",name);
            return "mf";

        }

        if(sortBy.equals("percentage-Return-3yr")){
            model.addAttribute("list",mFrepository.findByShortNameOrderByRet3Desc(name));
            model.addAttribute("name",name);
            return "mf";
        }

        if(sortBy.equals("percentage-Return-1yr")){
            model.addAttribute("list",mFrepository.findByShortNameOrderByRet1Desc(name));
            model.addAttribute("name",name);
            return "mf";
        }


        if(sortBy.equals("current-nav")){
            model.addAttribute("list",mFrepository.findByShortNameOrderByNavDesc(name));
            model.addAttribute("name",name);
            return "mf";
        }

        return "redirect:/mf/" + name;

   
    }






    @GetMapping("/404")
    public String error(){
        return "error";
    }

    @PostMapping("/calculate/{name}")
    public String calculate(@PathVariable("name") String name,@RequestParam("total_investment") String total_investment,
                            @RequestParam("Expected_Return_Rate") String Expected_Return_Rate,
                            @RequestParam(value="Time_Period" ,defaultValue="5") String Time_Period,
                            Model model){

    try{
        Double r=Double.parseDouble(Expected_Return_Rate)/100;      
        Double futureValue = Integer.parseInt(total_investment)*(Math.pow((1+r),Integer.parseInt(Time_Period)));
        Double increasedAmount=futureValue-Integer.parseInt(total_investment);

        

        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);


        model.addAttribute("increasedAmount", df.format(increasedAmount));
        model.addAttribute("futureValue", df.format(futureValue));
        model.addAttribute("list",mFrepository.findByShortName(name));



        return "mf";
    }catch(Exception e){
        return "redirect:/mf/" + name;
    }
       


    }






            




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








