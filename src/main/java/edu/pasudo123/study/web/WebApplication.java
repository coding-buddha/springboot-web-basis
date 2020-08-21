package edu.pasudo123.study.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan(basePackages = {"edu.pasudo123.study.web.member"})
@ComponentScan(basePackages = {
        "edu.pasudo123.study.web.config",
        "edu.pasudo123.study.web.index",
        "edu.pasudo123.study.web.member"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
