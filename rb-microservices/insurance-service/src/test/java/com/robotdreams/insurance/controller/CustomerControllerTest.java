package com.robotdreams.insurance.controller;

import com.robotdreams.insurance.model.dto.CustomerDTO;
import com.robotdreams.insurance.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    void findCustomerById() {
        // given

        // when

        // then
    }

    @Test
    void findAllCustomers() {
        // given

        // when

        // then
    }

    @Test
    void findCustomerBySsid() {
        // given

        // when

        // then
    }

    @Test
    void findCustomerByPhoneNumber() {
        // given

        // when

        // then
    }

    @Test
    void saveCustomer() {
        // given
        CustomerDTO customer = new CustomerDTO();
        customer.setFullName("Ahmet Mehmet");
        customer.setAddress("Tuzla Istanbul");
        customer.setSsidNumber("1234567890");
        customer.setId(1L);
        when(customerService.saveCustomer(any())).thenReturn(customer);

        // when
        CustomerDTO actual = this.customerController.saveCustomer(customer);

        // then
        //assertNotNull(actual);
        //assertNotNull(customer.getId());
        //assertEquals(customer.getSsidNumber(), actual.getSsidNumber());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(customer.getId()),
                () -> assertEquals(customer.getSsidNumber(), actual.getSsidNumber())
        );
    }

    @Test
    void updateCustomer() {
        // given

        // when

        // then
    }

    @Test
    void deleteCustomerById() {
        // given

        // when

        // then
    }
}