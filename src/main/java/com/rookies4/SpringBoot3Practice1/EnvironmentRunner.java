package com.rookies4.SpringBoot3Practice1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class EnvironmentRunner implements ApplicationRunner {
    private final MyEnvironment myEnvironment;

    public EnvironmentRunner(MyEnvironment myEnvironment){
        this.myEnvironment = myEnvironment;
    }

    @Override
    public void run(ApplicationArguments args){
        System.out.println("현재 모드: " +myEnvironment.getMode());
    }
}
