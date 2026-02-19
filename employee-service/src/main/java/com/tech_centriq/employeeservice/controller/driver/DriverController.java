package com.tech_centriq.employeeservice.controller.driver;

import com.tech_centriq.employeeservice.dto.request.driver.CreateDriverRequestDTO;
import com.tech_centriq.employeeservice.dto.request.user.UpdateDriverRequestDTO;
import com.tech_centriq.employeeservice.dto.responce.DriverResponseDTO;
import com.tech_centriq.employeeservice.entity.Driver;
import com.tech_centriq.employeeservice.service.driver.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class DriverController {

    private final DriverService driverService;

    @PostMapping("/drivers")
    @ResponseStatus(HttpStatus.CREATED)
    public DriverResponseDTO createDriver(@RequestBody CreateDriverRequestDTO request) {

        Driver driver = driverService.createDriver(request);
        return DriverResponseDTO.responseDTO(driver);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<DriverResponseDTO> getDriver(@PathVariable int id) {
        return ResponseEntity.ok(driverService.getDriver(id));
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<DriverResponseDTO>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/drivers-active")
    public ResponseEntity<List<DriverResponseDTO>> getActiveDrivers() {
        return ResponseEntity.ok(driverService.getActiveDrivers());
    }

    @GetMapping("/drivers-inactive")
    public ResponseEntity<List<DriverResponseDTO>> getInActiveDrivers() {
        return ResponseEntity.ok(driverService.getInActiveDrivers());
    }

    @DeleteMapping("drivers/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);

        return ResponseEntity.ok("Driver deactivated successfully.");
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable int id, @RequestBody UpdateDriverRequestDTO request) {

        DriverResponseDTO responseDTO = driverService.updateDriver(id, request);
        return ResponseEntity.ok(responseDTO);
    }
}
