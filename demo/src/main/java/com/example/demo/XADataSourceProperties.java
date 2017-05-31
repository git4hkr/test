package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "blogic.xadatasource")
public class XADataSourceProperties {
	private List<Map<String, Object>> props;
}
