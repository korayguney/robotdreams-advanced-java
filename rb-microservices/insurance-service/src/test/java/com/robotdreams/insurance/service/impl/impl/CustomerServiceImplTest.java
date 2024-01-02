package com.robotdreams.insurance.service.impl;

import com.robotdreams.insurance.mapper.CustomerEntityCustomerDTOMapper;
import com.robotdreams.insurance.model.Customer;
import com.robotdreams.insurance.model.dto.CustomerDTO;
import com.robotdreams.insurance.repository.CustomerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerEntityCustomerDTOMapper customerEntityCustomerDTOMapper;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void findCustomerTest() {
        // given
        long id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        String fullName = "Ali Veli";
        customer.setFullName(fullName);
        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
        // when(customerRepository.findById(Mockito.eq(id))).thenReturn(Optional.of(customer));

        // when
        Customer actual = customerService.findCustomer(id);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertNotNull(customer.getId()),
                () -> assertEquals(customer.getFullName(), fullName)
        );
    }

    @Test
    void findAllCustomersTest() {
        // given
        CustomerDTO customerDTO = CustomerDTO.builder().id(1).fullName("Ali Hasan").build();
        List<Customer> customerList = Arrays.asList(
                Customer.builder().id(1).fullName("Ali Hasan").build(),
                Customer.builder().id(2).fullName("Fatma Ayşe").build()
        );
        when(customerEntityCustomerDTOMapper.map(any(), any())).thenReturn(customerDTO);
        when(customerRepository.findAll()).thenReturn(customerList);

        // when
        List<CustomerDTO> actual = customerService.findAllCustomers();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(actual.size(), 2)
        );
    }

    @Test
    @Disabled
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

        // when

        // then
    }

    @Test
    void deleteCustomer() {
        // given

        // when

        // then
    }
}