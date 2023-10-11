package com.robotdreams.insurance.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private long id;
    private String fullName;
    private String address;
    private String ssidNumber;
    private String phoneNumber;
    private List<VehicleDTO> vehicleList = new ArrayList<>();
}
