package com.rookies4.SpringBoot3Practice1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyPropRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    private final MyPropProperties myPropProperties;

    // 생성자 주입
    public MyPropRunner(MyPropProperties myPropProperties) {
        this.myPropProperties = myPropProperties;
    }

    @Value("${myprop.username}")
    private String name;

    @Value("${myprop.port}")
    private String port;

    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        System.out.println("${myprop.username} : "+name);
        System.out.println("${myprop.port} : "+port);
        System.out.println("Name : " + myPropProperties.getName());
        System.out.println("Age : "+ myPropProperties.getAge());
        System.out.println("Email : "+myPropProperties.getEmail());

        logger.info("=== INFO 레벨 로그 ===");
        logger.info("Name : " + myPropProperties.getName());
        logger.info("Age : "+ myPropProperties.getAge());
        logger.info("Email : "+myPropProperties.getEmail());
        logger.debug("=== DEBUG 레벨 로그 ===");
        logger.debug("Name : ", myPropProperties.getName());
        logger.debug("Age : ", myPropProperties.getAge());
        logger.debug("Email : ", myPropProperties.getEmail());
    }

}
