package com.robotdreams.insurance.service;

import com.robotdreams.insurance.model.Vehicle;

public interface VehicleService {
    Vehicle findVehicleByPlate(String plate);
}
