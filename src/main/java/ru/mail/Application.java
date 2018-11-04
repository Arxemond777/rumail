package ru.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableScheduling - for cron taks
 * @EnableScheduling - for activate MBean
 */
@SpringBootApplication
@EnableScheduling
@EnableMBeanExport
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}