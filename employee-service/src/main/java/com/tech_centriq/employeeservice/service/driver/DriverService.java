package com.tech_centriq.employeeservice.service.driver;

import com.tech_centriq.employeeservice.dto.request.driver.CreateDriverRequestDTO;
import com.tech_centriq.employeeservice.dto.request.user.UpdateDriverRequestDTO;
import com.tech_centriq.employeeservice.dto.responce.DriverResponseDTO;
import com.tech_centriq.employeeservice.entity.Driver;
import com.tech_centriq.employeeservice.enums.UserRole;
import com.tech_centriq.employeeservice.enums.UserStatus;
import com.tech_centriq.employeeservice.repository.driver.DriverRepository;
import com.tech_centriq.employeeservice.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class DriverService {

    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Driver createDriver(CreateDriverRequestDTO createDriverRequestDTO) {

        if (userRepository.existsByEmail(createDriverRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        //Map DTO to Entity
        Driver driver = Driver.builder()
                .email(createDriverRequestDTO.getEmail())
//                .passwordHash(createDriverRequestDTO.getPasswordHash())
                .firstName(createDriverRequestDTO.getFirstName())
                .lastName(createDriverRequestDTO.getLastName())
                .phoneNumber(createDriverRequestDTO.getPhoneNumber())

                // System controlled fields
                .role(UserRole.DRIVER)
                .status(UserStatus.ACTIVE)

                // Driver specific fields
                .licenceCode(createDriverRequestDTO.getLicenceCode())
                .isActive(true)
                .build();

        return driverRepository.save(driver);
    }

    public DriverResponseDTO getDriver(int id) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver " + id + " not found"));

        return DriverResponseDTO.responseDTO(driver);
    }

    public List<DriverResponseDTO> getAllDrivers() {

        return driverRepository.findAll()
                .stream()
                .map(DriverResponseDTO::responseDTO)
                .toList();
    }

    public List<DriverResponseDTO> getActiveDrivers() {
        return driverRepository.findByIsActiveTrue()
                .stream()
                .map(DriverResponseDTO::responseDTO)
                .toList();
    }

    public List<DriverResponseDTO> getInActiveDrivers() {
        return driverRepository.findByIsActiveFalse()
                .stream()
                .map(DriverResponseDTO::responseDTO)
                .toList();
    }

    // Uncomment the below line when doing Spring Security
    //@PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteDriver(int id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver " + id + " not found"));

        if (!driver.isActive()) {
            throw new IllegalArgumentException("Driver " + id + " already deactivated");
        }

        // Soft delete. This will only set the status to inactive for History, Auditing purposes, etc.
        driver.setActive(false);
        driver.setStatus(UserStatus.IN_ACTIVE);

        driverRepository.save(driver);
    }

    @Transactional
    public DriverResponseDTO updateDriver(int id, UpdateDriverRequestDTO updateDriverRequestDTO) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver " + id + " not found"));

        if (updateDriverRequestDTO.getFirstName() != null) {
            driver.setFirstName(updateDriverRequestDTO.getFirstName());
        }

        if (updateDriverRequestDTO.getLastName() != null) {
            driver.setLastName(updateDriverRequestDTO.getLastName());
        }

        if (updateDriverRequestDTO.getPhoneNumber() != null) {
            driver.setPhoneNumber(updateDriverRequestDTO.getPhoneNumber());
        }

        if (updateDriverRequestDTO.getStatus() != null) {
            driver.setStatus(updateDriverRequestDTO.getStatus());
        }

        if (updateDriverRequestDTO.getLicenceCode() != null) {
            driver.setLicenceCode(updateDriverRequestDTO.getLicenceCode());
        }

        return DriverResponseDTO.responseDTO(driver);

    }
}
