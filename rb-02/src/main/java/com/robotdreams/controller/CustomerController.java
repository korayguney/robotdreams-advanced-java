package com.robotdreams.controller;

import com.robotdreams.model.Customer;
import com.robotdreams.model.dto.CustomerDTO;
import com.robotdreams.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(@Qualifier("CustomerServiceImpl") CustomerService customerService) {
        this.customerService = customerService;
    }

    //@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    //public ResponseEntity<Customer> findCustomerById(@PathVariable long id) {
    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable long id) {
        return customerService.findCustomer(id);
    }

    @GetMapping("/customers")
    public List<CustomerDTO> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/customers/ssid")
    public Customer findCustomerBySsid(@RequestParam long ssid) {
        return customerService.findCustomerBySsid(ssid);
    }

    @GetMapping("/customers/phone-number")
    Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerService.findCustomerByPhoneNumber(phoneNumber);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomerById(@PathVariable long id) {
        customerService.deleteCustomer(id);
    }
}
