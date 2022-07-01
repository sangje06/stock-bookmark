package com.sj.stbm.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "props")
public class PropsConfig {
	
	private Map<String, String> orgs;

}
