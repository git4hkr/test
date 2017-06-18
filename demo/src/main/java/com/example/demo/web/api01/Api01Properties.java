package com.example.demo.web.api01;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties("api01")
@Validated
@Data
public class Api01Properties {
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@NotNull
	private LocalDateTime startDateTime;
	@NotNull
	private InetAddress serverAddress;

	private UUID uuid;
}
