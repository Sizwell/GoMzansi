package com.tech_centriq.employeeservice.dto.request.user;

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

public abstract class CreateUserRequestDTO {

    private String email;

    private String password;

    private String firstName;
    private String lastName;
    private String phoneNumber;
}
