package com.example.demo.web.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties("common")
@Validated
@Data
public class CommonProperties {
	private String version;
}
