package com.medshop.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MedShopFrontEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedShopFrontEndApplication.class, args);
    }

}
