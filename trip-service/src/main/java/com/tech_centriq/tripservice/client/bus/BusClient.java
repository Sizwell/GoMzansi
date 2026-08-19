package com.tech_centriq.tripservice.client.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BusClient {

    private final WebClient.Builder webClientBuilder;

    public BusValidationResponseDTO getBus(String busNumber) {

        return webClientBuilder.build()
                .get()
                .uri("http://BUS-SERVICE/api/buses/number/{busNumber}", busNumber)
                .retrieve()
                .bodyToMono(BusValidationResponseDTO.class)
                .block();
    }
}
