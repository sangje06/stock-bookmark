package com.sj.stbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.sj.stbm.config.PropsConfig;

@SpringBootApplication
@EnableRedisHttpSession
@EnableScheduling
@EnableConfigurationProperties(PropsConfig.class)
public class StbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(StbmApplication.class, args);
	}

}
