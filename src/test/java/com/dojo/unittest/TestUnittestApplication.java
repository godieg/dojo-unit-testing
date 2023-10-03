package com.dojo.unittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestUnittestApplication {

	public static void main(String[] args) {
		SpringApplication.from(UnittestApplication::main).with(TestUnittestApplication.class).run(args);
	}

}
