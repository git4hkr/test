package com.example.demo;

import java.util.Collections;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import oldtricks.blogic.springcontext.BLogicFunctionEnhanceBeanPostProcessor;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class,
		MybatisAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * BLogicフレームワークを適用します。
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public static BLogicFunctionEnhanceBeanPostProcessor blogicFunctionEnhanceBeanPostProcessor() {
		return new BLogicFunctionEnhanceBeanPostProcessor();
	}

	/**
	 * Date and Time API（JSR-310)の日時クラスへの型変換を可能にします。
	 *
	 * @return
	 */
	@Bean
	public ConversionService conversionService() {
		FormattingConversionServiceFactoryBean factory = new FormattingConversionServiceFactoryBean();
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setUseIsoFormat(true);
		factory.setFormatterRegistrars(Collections.singleton(registrar));
		factory.afterPropertiesSet();
		return factory.getObject();
	}
}
