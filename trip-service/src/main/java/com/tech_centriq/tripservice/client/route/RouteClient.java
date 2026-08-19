package com.tech_centriq.tripservice.client.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RouteClient {

    private final WebClient.Builder webClientBuilder;

    public RouteValidationResponseDTO getRoute(String routeCode) {

        return webClientBuilder.build()
                .get()
                .uri("http://route-service/api/routes/{routeCode}", routeCode)
                .retrieve()
                .bodyToMono(RouteValidationResponseDTO.class)
                .block();
    }
}
