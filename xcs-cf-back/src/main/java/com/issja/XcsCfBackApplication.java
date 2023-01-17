package com.issja;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.issja.mapper")
public class XcsCfBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcsCfBackApplication.class, args);
    }

}
