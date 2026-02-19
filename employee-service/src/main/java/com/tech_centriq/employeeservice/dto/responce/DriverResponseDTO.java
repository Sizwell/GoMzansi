package com.tech_centriq.employeeservice.dto.responce;

import com.tech_centriq.employeeservice.entity.Driver;
import com.tech_centriq.employeeservice.enums.LicenceCode;
import com.tech_centriq.employeeservice.enums.UserStatus;
import lombok.*;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DriverResponseDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LicenceCode licenceCode;
    private UserStatus status;

    public static DriverResponseDTO responseDTO(Driver driver) {
        return DriverResponseDTO.builder()
                .id(driver.getUserId())
                .email(driver.getEmail())
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .phoneNumber(driver.getPhoneNumber())
                .licenceCode(driver.getLicenceCode())
                .status(driver.getStatus())
                .build();
    }

}
