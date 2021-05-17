package com.sandesh.techmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages= {"com.sandesh.techmart"})
public class TechmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechmartApplication.class, args);
    }

}
