package com.robotdreams.service;

import com.robotdreams.model.Customer;
import com.robotdreams.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    Customer findCustomer(long id);
    List<CustomerDTO> findAllCustomers();
    Customer findCustomerBySsid(long ssid);
    Customer findCustomerByPhoneNumber(String phoneNumber);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    void deleteCustomer(long id);
}
