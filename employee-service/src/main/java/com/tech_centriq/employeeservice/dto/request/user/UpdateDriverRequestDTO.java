package com.tech_centriq.employeeservice.dto.request.user;

import com.tech_centriq.employeeservice.enums.LicenceCode;
import com.tech_centriq.employeeservice.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class UpdateDriverRequestDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private UserStatus status;

    private LicenceCode licenceCode;
}
