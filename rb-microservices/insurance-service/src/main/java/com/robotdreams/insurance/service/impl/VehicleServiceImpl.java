package com.robotdreams.insurance.service.impl;

import com.robotdreams.insurance.exception.VehicleNotFoundException;
import com.robotdreams.insurance.model.Vehicle;
import com.robotdreams.insurance.repository.VehicleRepository;
import com.robotdreams.insurance.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional(readOnly = true)
    public Vehicle findVehicleByPlate(String plate) {
        return vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with plate " + plate + " is not found!"));
    }
}
