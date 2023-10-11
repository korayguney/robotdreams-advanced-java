package com.robotdreams.mapper;

import com.robotdreams.model.Customer;
import com.robotdreams.model.dto.CustomerDTO;
import com.robotdreams.model.dto.VehicleDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CustomerEntityCustomerDTOMapper implements BaseMapper<CustomerDTO, Customer> {

    private final VehicleEntityVehicleDTOMapper vehicleEntityVehicleDTOMapper;

    public CustomerEntityCustomerDTOMapper(VehicleEntityVehicleDTOMapper vehicleEntityVehicleDTOMapper) {
        this.vehicleEntityVehicleDTOMapper = vehicleEntityVehicleDTOMapper;
    }


    @Override
    public CustomerDTO map(Customer customer, Object... params) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setSsidNumber(String.valueOf(customer.getSsidNumber()).substring(1, 4) + "**********");

        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        if (Objects.nonNull(customer.getVehicleList()))
            customer.getVehicleList().forEach(v -> vehicleDTOList.add(vehicleEntityVehicleDTOMapper.map(v, null)));
        customerDTO.setVehicleList(vehicleDTOList);

        return customerDTO;
    }
}
