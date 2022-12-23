package com.shino.onlineanswer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shino.onlineanswer.Mapper")
public class OnlineAnswerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineAnswerApplication.class, args);
    }

}
