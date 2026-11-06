package org.biblioteca.prestamo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClienteServiciosConfig {
    @Bean
    RestClient libroRestClient(RestClient.Builder builder, @Value("${services.libro.url}") String url) {
        return builder.baseUrl(url).build();
    }

    @Bean
    RestClient lectorRestClient(RestClient.Builder builder, @Value("${services.lector.url}") String url) {
        return builder.baseUrl(url).build();
    }
}
