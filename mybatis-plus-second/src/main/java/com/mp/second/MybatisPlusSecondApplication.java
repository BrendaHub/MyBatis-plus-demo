package com.mp.second;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.mp.second.mappers")
public class MybatisPlusSecondApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MybatisPlusSecondApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

}
