package com.medshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.medshop.common.entity", "com.medshop.admin.user"})
public class MedShopBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedShopBackEndApplication.class, args);
    }

}
