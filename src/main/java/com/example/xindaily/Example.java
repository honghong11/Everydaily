package com.example.xindaily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {
    @RequestMapping("/")
    //http://localhost:8080/?age=10&wangxinxin 参数方式
    String home(Integer age,String passwd){
        int result = age *10;
        return passwd+result;
    }

    public static void main(String []args){
        SpringApplication.run(Example.class,args);
    }
}
