package com.robotdreams.insurance.repository;

import com.robotdreams.insurance.model.Vehicle;
import com.robotdreams.insurance.service.VehicleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlate(String vehiclePlate);
}
