package com.dlj.demo.learn_restutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyRestUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRestUtilApplication.class, args);
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		System.out.println("-----restTemplate----------");
		RestTemplate restTemplate = new RestTemplate();
		
		// 设置拦截器，用于http basic的认证等
		//restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}
