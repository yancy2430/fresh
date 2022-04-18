package com.hzl.fresh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hzl.fresh.mapper")
@SpringBootApplication(scanBasePackages = "com.hzl")
public class FreshApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshApplication.class, args);
    }

}
