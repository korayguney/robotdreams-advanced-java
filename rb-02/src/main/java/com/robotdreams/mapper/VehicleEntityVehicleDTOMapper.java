package com.robotdreams.mapper;

import com.robotdreams.model.Car;
import com.robotdreams.model.Motorcycle;
import com.robotdreams.model.Vehicle;
import com.robotdreams.model.dto.VehicleDTO;
import com.robotdreams.model.enums.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleEntityVehicleDTOMapper implements BaseMapper<VehicleDTO, Vehicle> {
    @Override
    public VehicleDTO map(Vehicle vehicle, Object... params) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setPlate(vehicle.getPlate());
        vehicleDTO.setYear(vehicle.getYear());
        //if (vehicle instanceof Car) {
        //    vehicleDTO.setVehicleType(VehicleType.CAR);
        //} else if (vehicle instanceof Motorcycle) {
        //    vehicleDTO.setVehicleType(VehicleType.MOTORCYCLE);
        //}
        vehicleDTO.setVehicleType((vehicle instanceof Car) ? VehicleType.CAR : VehicleType.MOTORCYCLE);
        return vehicleDTO;
    }
}
