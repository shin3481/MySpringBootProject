package com.rookies4.SpringBoot3Practice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot3Practice1Application {

	public static void main(String[] args) {

		//SpringApplication.run(SpringBoot3Practice1Application.class, args);
        SpringApplication application = new SpringApplication(SpringBoot3Practice1Application.class);
        //Application 타입을 변경하기
        application.setWebApplicationType(WebApplicationType.SERVLET); //Spring MVC
        application.run(args);
	}

}
