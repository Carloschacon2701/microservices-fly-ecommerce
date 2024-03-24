package org.carlos.apigateway.Config;

import org.carlos.apigateway.Filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GatewayConfig {
    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("chat-service", r -> r.path("/chat-service/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://chat-service")
                )
                .route("fly_core", r -> r.path("/fly-core/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://fly_core")
                )
                .build();
    }
}
