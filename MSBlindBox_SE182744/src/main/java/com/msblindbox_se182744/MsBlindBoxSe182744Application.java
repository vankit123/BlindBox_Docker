package com.msblindbox_se182744;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsBlindBoxSe182744Application {

    public static void main(String[] args) {
        SpringApplication.run(MsBlindBoxSe182744Application.class, args);




    }

}
