package com.tech_centriq.employeeservice.entity;

import com.tech_centriq.employeeservice.enums.LicenceCode;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "drivers")
@DiscriminatorValue("DRIVER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Driver extends User{

    @Enumerated(EnumType.STRING)
    @Column(name = "licence_code", nullable = false)
    private LicenceCode licenceCode;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}
