package com.koi151.flasheat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FlasheatApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlasheatApplication.class, args);
	}

}
