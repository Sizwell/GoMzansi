package com.tech_centriq.tripservice.client.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class DriverClient {

    private final WebClient.Builder webClientBuilder;

    public DriverValidationResponseDTO getDriver(Long id) {

        return webClientBuilder.build()
                .get()
                .uri("http://employee-service/api/users/driver/{id}", id)
                .retrieve()
                .bodyToMono(DriverValidationResponseDTO.class)
                .block();
    }
}
