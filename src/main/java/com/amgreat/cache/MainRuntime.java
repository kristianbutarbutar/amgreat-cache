package com.amgreat.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = "com.amgreat.cache, com.amgreat.cache.vo, com.amgreat.cache.ctrl, com.amgreat.cache.repository")
public class MainRuntime 
{
    public static void main(String[] args) {
		SpringApplication.run(MainRuntime.class, args);
	}
}
