package com.tech_centriq.employeeservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "admins")
@DiscriminatorValue("ADMIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Admin extends User{

    @Column(name = "admin_level", nullable = false)
    private int adminLevel;

    @Column(name = "can_manage_users", nullable = false)
    private boolean canManageUsers;
}
