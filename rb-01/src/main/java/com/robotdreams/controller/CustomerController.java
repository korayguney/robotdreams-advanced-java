package com.robotdreams.controller;

import com.robotdreams.models.Customer;
import com.robotdreams.models.Vehicle;
import com.robotdreams.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService = new CustomerService();

    public List<Customer> findAllCustomer() {
        return customerService.findAll();
    }

    public Customer findCustomer(long id) {
        return customerService.findById(id);
    }

    public void saveCustomer(Customer customer) {
        customerService.saveToDatabase(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerService.deleteFromDatabase(customer);
    }

    public List<Vehicle> findAllVehicleOfCustomerWithSSID(long ssid) {
        return customerService.findAllVehicleOfCustomerWithSSID(ssid);
    }

}
