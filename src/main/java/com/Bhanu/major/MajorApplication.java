package com.Bhanu.major;

//import jdk.internal.icu.impl.Punycode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ComponentScan(basePackages = "com.Bhanu.major")
@SpringBootApplication
public class MajorApplication {
	@Autowired
	static BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(MajorApplication.class, args);

	}

}
