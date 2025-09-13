package com.example.order_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

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


