package com.rookies4.myspringboot.runner;


import com.rookies4.myspringboot.config.vo.CustomVO;
import com.rookies4.myspringboot.property.MyBootProperties;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyPropsRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private String age;

    @Autowired
    private Environment environment;

    @Autowired
    private MyBootProperties myBootProperties;

    @Autowired
    private CustomVO customVO;

    //Logger 객체생성
    private Logger logger = LoggerFactory.getLogger(MyPropsRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("Logger 구현 객체명 = {}",logger.getClass().getName());

        System.out.println();

        logger.info("현재 활성화된 CustomerVO Bean = " + customVO);

        logger.info("MyBootProperties.getName() = " + myBootProperties.getName());
        logger.info("MyBootProperties.getAge() = " + myBootProperties.getAge());
        logger.info("MyBootProperties.getFullName() = " + myBootProperties.getFullName());


        logger.info("Properties myboot.name =" + name);
        logger.info("Properties myboot.age =" + age);
        logger.info("Properties myboot.fullName =" + environment.getProperty("myboot.fullName"));

        logger.debug("VM Arguments  "+ args.containsOption( "foo")); //false
        logger.debug("Program Arguments = "+args.containsOption( "bar")); //true

        //Program Argument의 모든 이름을 출력하기
        for(String argName: args.getOptionNames()){
            System.out.println("아규먼트 이름 = " + argName);
        }
        //args.getOptionNames()의 리턴타임 Set<String>
        //Iterable의 foreach(Consumer)메서드 호출하기
        //1. 익명의 Inner class( Anonymous Inner Class)
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("아규먼트 이름 = " +s);
            }
        });
        System.out.println("===> 람다함수");
        //2. 함수형 인터페이스 (람다함수)
        args.getOptionNames().forEach(name -> logger.debug(name));
        System.out.println("===> Method Reference");
        //3. Method Reference (아규먼트를 생략한 람다함수)
        //args.getOptionNames().forEach(System.out::println);
        args.getOptionNames().forEach(System.out::println);
    }
}
