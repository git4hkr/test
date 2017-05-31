package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import oldtricks.blogic.springcontext.BLogicFunctionEnhanceBeanPostProcessor;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean
	public static BLogicFunctionEnhanceBeanPostProcessor blogicFunctionEnhanceBeanPostProcessor() {
		return new BLogicFunctionEnhanceBeanPostProcessor();
	}

}
