package com.tech_centriq.employeeservice.dto.request.driver;

import com.tech_centriq.employeeservice.dto.request.user.CreateUserRequestDTO;
import com.tech_centriq.employeeservice.enums.LicenceCode;
import jakarta.validation.constraints.NotNull;
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

public class CreateDriverRequestDTO extends CreateUserRequestDTO {

    @NotNull
    private LicenceCode licenceCode;
}
