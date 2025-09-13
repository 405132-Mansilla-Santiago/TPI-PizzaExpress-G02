package com.example.delivery_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    private final AppProps appProps;

    public SpringDocConfig(AppProps appProps) {
        this.appProps = appProps;
    }

    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .title(appProps.getName())
                .description(appProps.getDesc())
                .version(appProps.getVersion())
                .contact(new Contact()
                        .name(appProps.getDevName())
                        .email(appProps.getDevEmail()));

        Server server = new Server()
                .url(appProps.getUrl())
                .description(appProps.getDesc());

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addServersItem(server);
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
}
