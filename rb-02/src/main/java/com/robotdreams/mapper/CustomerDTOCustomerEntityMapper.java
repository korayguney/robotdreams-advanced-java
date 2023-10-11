package com.robotdreams.mapper;

import com.robotdreams.model.Customer;
import com.robotdreams.model.dto.CustomerDTO;
import com.robotdreams.model.dto.VehicleDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDTOCustomerEntityMapper implements BaseMapper<Customer, CustomerDTO> {


    @Override
    public Customer map(CustomerDTO customerDTO, Object... params) {
        Customer customer = Customer.builder()
                .fullName(customerDTO.getFullName())
                .ssidNumber(Long.valueOf(customerDTO.getSsidNumber()))
                .address(customerDTO.getAddress())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();
        if(customerDTO.getId() != 0) customer.setId(customerDTO.getId());
        return customer;
    }
}
