package com.msblindbox_se182744;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    @Value("${app.brand-service-path:NOT_FOUND}")
    private String brandServicePath;

    @PostConstruct
    public void logBrandServicePath() {
        System.out.println("====================================================");
        System.out.println(">>> [DEBUG] app.brand-service-path = " + brandServicePath);
        System.out.println("====================================================");
    }
}