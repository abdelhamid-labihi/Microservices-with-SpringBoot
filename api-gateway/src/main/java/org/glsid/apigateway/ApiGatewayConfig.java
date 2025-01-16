package org.glsid.apigateway;

import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// this is standard configuration for the gateway we use it only when we have a fixed public API that we want to consume
@Configuration
public class ApiGatewayConfig {
    // direct access via /products/** or /customers/**
//    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(p -> p.path("/products/**")
                        .uri("lb://INVENTORY-SERVICE"))

                .route(p -> p.path("/customers/**")
                        .uri("lb://CUSTOMER-SERVICE"))
                .build();
    }

    // we must add the service name before the path /CUSTOMER-SERVICE/customers/**
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties props) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, props);
    }

}
