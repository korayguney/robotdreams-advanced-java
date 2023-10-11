package com.robotdreams.insurance.service.impl;


import com.robotdreams.insurance.exception.CustomerNotFoundException;
import com.robotdreams.insurance.model.Customer;
import com.robotdreams.insurance.model.dto.CustomerDTO;
import com.robotdreams.insurance.repository.CustomerRepository;
import com.robotdreams.insurance.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerServiceImpl2")
public class CustomerServiceImpl2 implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl2(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomer(long id) {
        System.out.println("Inside CustomerServiceImpl2");
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID : " + id));
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return null;
    }

    @Override
    public Customer findCustomerBySsid(long ssid) {
        return null;
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public void deleteCustomer(long id) {

    }
}
