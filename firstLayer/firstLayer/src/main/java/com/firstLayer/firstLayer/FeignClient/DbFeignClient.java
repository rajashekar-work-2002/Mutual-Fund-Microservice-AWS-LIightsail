package com.firstLayer.firstLayer.FeignClient;


import com.firstLayer.firstLayer.entity.CalculatedVales;
import com.firstLayer.firstLayer.entity.MFEntity;
import com.firstLayer.firstLayer.entity.ReturnsCalculate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@FeignClient("DBLAYER")
public interface DbFeignClient {
    @GetMapping("/")
    public ResponseEntity<List<MFEntity>> fetchListOfAllMutualFundSchemes(@RequestParam List<String> list);

    @GetMapping("/mfbyname")
    public ResponseEntity<List<MFEntity>> AllMfByName(@RequestParam("name") String name);


    @GetMapping("/mfNavOnDate")
    public ResponseEntity<CalculatedVales> getNavOnDate(@RequestParam("id") String code) throws IOException;


    @PostMapping("/sort")
    public ResponseEntity<List<MFEntity>> sort( @RequestParam("sortby") String sortBy,
                                                @RequestParam("name")String name)throws IOException;


    @PostMapping("/calculate")
    public ResponseEntity<ReturnsCalculate> calculate(@RequestParam("name") String name, @RequestParam("total_investment") String total_investment,
                                                      @RequestParam("Expected_Return_Rate") String Expected_Return_Rate,
                                                      @RequestParam(value="Time_Period" ,defaultValue="5") String Time_Period
    )throws Exception;

}
