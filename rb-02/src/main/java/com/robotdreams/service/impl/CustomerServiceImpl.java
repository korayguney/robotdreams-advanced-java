package com.robotdreams.service.impl;

import com.robotdreams.exception.CustomerNotFoundException;
import com.robotdreams.mapper.CustomerDTOCustomerEntityMapper;
import com.robotdreams.mapper.CustomerEntityCustomerDTOMapper;
import com.robotdreams.model.Customer;
import com.robotdreams.model.dto.CustomerDTO;
import com.robotdreams.repository.CustomerRepository;
import com.robotdreams.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CustomerServiceImpl")
@Primary
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEntityCustomerDTOMapper customerEntityCustomerDTOMapper;
    private final CustomerDTOCustomerEntityMapper customerDTOCustomerEntityMapper;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerEntityCustomerDTOMapper customerEntityCustomerDTOMapper,
                               CustomerDTOCustomerEntityMapper customerDTOCustomerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityCustomerDTOMapper = customerEntityCustomerDTOMapper;
        this.customerDTOCustomerEntityMapper = customerDTOCustomerEntityMapper;
    }

    @Override
    public Customer findCustomer(long id) {
        System.out.println("Inside CustomerServiceImpl");
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID : " + id));
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerList.stream().forEach(c -> customerDTOList.add(customerEntityCustomerDTOMapper.map(c, null)));
        return customerDTOList;
    }

    @Override
    public Customer findCustomerBySsid(long ssid) {
        return customerRepository.findCustomerBySsid(ssid);
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerDTOCustomerEntityMapper.map(customerDTO);
        return customerEntityCustomerDTOMapper.map(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(long id) {
        Customer foundCustomer = findCustomer(id);
        customerRepository.delete(foundCustomer);
    }
}
