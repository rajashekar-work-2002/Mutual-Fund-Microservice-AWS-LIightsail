package com.firstLayer.firstLayer.controller;


import com.firstLayer.firstLayer.FeignClient.DbFeignClient;
import com.firstLayer.firstLayer.entity.CalculatedVales;
import com.firstLayer.firstLayer.entity.MFEntity;
import com.firstLayer.firstLayer.entity.ReturnsCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class MfTool {

//    @Autowired
//    private MFTool mftool;

//    @Autowired
//    private MFrepository mFrepository;

//    private Map<String, Input> dictionary = new HashMap<>();


    @Autowired
    private DbFeignClient dbFeignClient;



    @GetMapping("/")
    public String homedata(Model model) {

        String[] mf={"149928", "118587","118556","112086","103191","119822","152156","121546","141808","100482"};
        List<String> list=new ArrayList<>();
        list= Arrays.asList(mf);

        List<MFEntity> mflist=new ArrayList<>();
        mflist=dbFeignClient.fetchListOfAllMutualFundSchemes(list).getBody();

        model.addAttribute("list",mflist);
        return "home";
    }











    @GetMapping("/mf/{name}")
    public String AllMfByName(@PathVariable("name") String name ,Model model) {

        model.addAttribute("list",dbFeignClient.AllMfByName(name).getBody());
        model.addAttribute("name",name);
        return "mf";

    }









    @GetMapping("/mf/code/{id}")
    public String getNavOnDate(@PathVariable("id") String code,Model model) throws IOException {
        System.out.println("000000000000000000000000000000000000000" + code);
        CalculatedVales calculatedVales = dbFeignClient.getNavOnDate(code).getBody();
        System.out.println(calculatedVales);
        model.addAttribute("keySet", calculatedVales.getKeys());
        model.addAttribute("values", calculatedVales.getValues());
        model.addAttribute("year", calculatedVales.getYear());
        model.addAttribute("per", calculatedVales.getPercentage());

        return "barchart";

    }





    @PostMapping("/sort/{sortby}/{name}")
    public String sort( @PathVariable("sortby") String sortBy,
                        @PathVariable("name")String name, Model model)throws IOException {



            model.addAttribute("list",dbFeignClient.sort(sortBy,name).getBody());
            model.addAttribute("name",name);
            return "mf";

    }






    @GetMapping("/404")
    public String error(){
        return "error";
    }



    @PostMapping("/calculate/{name}")
    public String calculate(@PathVariable("name") String name,@RequestParam(value = "total_investment",defaultValue="50000") String total_investment,
                            @RequestParam(value = "Expected_Return_Rate",defaultValue="10") String Expected_Return_Rate,
                            @RequestParam(value="Time_Period" ,defaultValue="5") String Time_Period,
                            Model model){
try {
    ReturnsCalculate returnsCalculate = dbFeignClient.calculate(name, total_investment, Expected_Return_Rate, Time_Period).getBody();
    model.addAttribute("increasedAmount", returnsCalculate.getIncreasedAmount());
    model.addAttribute("futureValue", returnsCalculate.getFutureValue());
    model.addAttribute("list", returnsCalculate.getList());
    return "mf";
}catch (Exception e){
    return "redirect:/mf/" + name;
}


    }






            


//
//
//    @GetMapping("/database")
//    public String database(Model model) throws IOException{
//        List<SchemeNameCodePair> inputList = mftool.allSchemes();
//        String[] mf={"ICICI","SBI","Groww","Nippon","Franklin","Axis","Zerodha","HDFC","Kotak","UTI","Quant","Prag"};
//        Map<String,Integer> check=new HashMap<>();
//        String path;
//        mFrepository.deleteAll();
//        for(SchemeNameCodePair str : inputList){
//
//            for(String i : mf){
//
//                if(check.getOrDefault(i,0)>60){
//                    continue;
//                }
//                if(str.getName().contains(i)){
//                    // outputList.add(str.getName());
//                    MFEntity object=new MFEntity();
//                    object.setName(str.getName());
//                    path= "/images/" + str.getName().split(" ")[0] + ".png";
//                    object.setPath(path);
//                    object.setCode(str.getCode());
//                    DecimalFormat df = new DecimalFormat("#.##");
//                    df.setMaximumFractionDigits(2);
//
//                    object.setRet1( Double.parseDouble(df.format( getReturnPercent(1,str.getCode()))  ) );
//                    object.setRet3( Double.parseDouble(df.format( getReturnPercent(3,str.getCode()))));
//                    object.setNav(mftool.getCurrentNav(str.getCode()).doubleValue());
//                    object.setShortName(i);
//                    mFrepository.save(object);
//                    check.put(i, check.getOrDefault(i,0)+1);
//                    break;
//                }
//
//            }
//
//        }
//        model.addAttribute("msg", mFrepository.findAll());
//
//    return "temp";
//
//    }
//











}








