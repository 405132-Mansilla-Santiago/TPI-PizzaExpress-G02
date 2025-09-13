package com.example.inventory_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProps {
    private String url;
    private String name;
    private String desc;
    private String version;
    private String devName;
    private String devEmail;
}


