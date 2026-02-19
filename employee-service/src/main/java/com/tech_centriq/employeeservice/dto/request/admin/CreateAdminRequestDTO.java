package com.tech_centriq.employeeservice.dto.request.admin;

import com.tech_centriq.employeeservice.dto.request.user.CreateUserRequestDTO;
import lombok.experimental.SuperBuilder;

@SuperBuilder

public class CreateAdminRequestDTO extends CreateUserRequestDTO {

    private int adminLevel;
    private boolean canManageUsers;
}
