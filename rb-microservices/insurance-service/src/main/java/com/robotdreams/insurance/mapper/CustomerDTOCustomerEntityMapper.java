package com.robotdreams.insurance.mapper;

import com.robotdreams.insurance.model.Customer;
import com.robotdreams.insurance.model.dto.CustomerDTO;
import org.springframework.stereotype.Component;

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
