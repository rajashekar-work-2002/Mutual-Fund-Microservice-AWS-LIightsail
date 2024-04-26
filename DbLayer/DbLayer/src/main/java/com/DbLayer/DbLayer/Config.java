package com.DbLayer.DbLayer;

import com.webencyclop.core.mftool.MFTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MFTool mftool(){
        return new MFTool();
   }
}
