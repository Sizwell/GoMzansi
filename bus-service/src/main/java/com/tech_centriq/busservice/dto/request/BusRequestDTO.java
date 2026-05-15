package com.tech_centriq.busservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Schema(description = "Bus creation request")
public class BusRequestDTO {

    @Schema(example = "CAA 123-456")
    private String plateNumber;

    @Schema(example = "78")
    private int capacity;

    @Schema(example = "MAN - Lion's Explorer")
    private String model;

}
