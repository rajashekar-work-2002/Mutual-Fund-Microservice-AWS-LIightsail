package com.firstLayer.firstLayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FirstLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstLayerApplication.class, args);
	}

}
