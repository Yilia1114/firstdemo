package com.example.firstdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HomeController {


    @Value("${OPENSHIFT_MYSQL_DB_HOST}")
    private String valueDbHost;

    @Value("${OPENSHIFT_MYSQL_DB_USERNAME}")
    private String valueDbUsername;

    @Value("${OPENSHIFT_MYSQL_DB_PASSWORD}")
    private String valueDbPassword;

    private final Environment environment;

    @GetMapping("/")
    public String home() {

        String dbHost = System.getenv().get("OPENSHIFT_MYSQL_DB_HOST");
        String dbUsername = System.getenv().get("OPENSHIFT_MYSQL_DB_USERNAME");
        String dbPassword = System.getenv().get("OPENSHIFT_MYSQL_DB_PASSWORD");


        log.info("DB Host: " + dbHost + "UserName：" + dbUsername + "Password：" +dbPassword +"| get to @Value");

        log.info("DB Host: " + valueDbHost + "UserName：" + valueDbUsername + "Password：" +valueDbPassword+"| is use getenv().get");

        return "Hello World";
    }


}
