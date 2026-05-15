package com.tech_centriq.busservice.dto.request;

import com.tech_centriq.busservice.enums.BusStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBusRequestDTO {

    @Schema(example = "CAA 123-456")
    private String plateNumber;

    @Schema(example = "78")
    private Integer capacity;

    @Schema(example = "MAN - Lion's Explorer")
    private String model;

    @Schema(example = "ACTIVE")
    private BusStatus status;

    @Schema(example = "true")
    private Boolean isActive;

}
