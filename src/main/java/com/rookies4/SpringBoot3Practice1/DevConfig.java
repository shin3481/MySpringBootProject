package com.rookies4.SpringBoot3Practice1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DevConfig {
    @Bean
    public MyEnvironment myEnvironment(){
        MyEnvironment env = new MyEnvironment();
        env.setMode("개발환경");
        return env;
    }

}
