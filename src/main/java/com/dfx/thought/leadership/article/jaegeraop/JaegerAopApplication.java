package com.dfx.thought.leadership.article.jaegeraop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dfx.thought"})
@EnableFeignClients
public class JaegerAopApplication {
	public static void main(String[] args) {
		SpringApplication.run(JaegerAopApplication.class, args);
	}
}
