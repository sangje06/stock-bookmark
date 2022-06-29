package com.sj.stbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableScheduling
public class StbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(StbmApplication.class, args);
	}

}
