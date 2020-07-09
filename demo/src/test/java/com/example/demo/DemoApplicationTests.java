package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootApplication(scanBasePackages={"com/example/demo"})
public class DemoApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationTests.class, args);
    }
}

