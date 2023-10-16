package com.robotdreams.insurance.service.impl;

import com.robotdreams.insurance.model.Customer;
import com.robotdreams.insurance.model.Insurance;
import com.robotdreams.insurance.model.Vehicle;
import com.robotdreams.insurance.model.dto.*;
import com.robotdreams.insurance.repository.CustomerRepository;
import com.robotdreams.insurance.repository.InsuranceRepository;
import com.robotdreams.insurance.service.CustomerService;
import com.robotdreams.insurance.service.InsuranceService;
import com.robotdreams.insurance.service.VehicleService;
import com.robotdreams.insurance.exception.CreditCardValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final CustomerRepository customerRepository; // Not recommended!
    private final CustomerService customerService;
    private final VehicleService vehicleService;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public InsurancePaymentResponse payForInsurancePolicy(InsurancePaymentRequest paymentRequest) {
        // get Customer entity from database
        // Customer customer = customerRepository.getById(paymentRequest.getCustomerID()); // Not recommended!
        Customer customer = customerService.findCustomer(paymentRequest.getCustomerID());
        Vehicle vehicle = vehicleService.findVehicleByPlate(paymentRequest.getVehiclePlate());

        // validate credit card number
        CreditCardValidationRequest validationRequest = CreditCardValidationRequest.builder()
                .customerId(paymentRequest.getCustomerID())
                .creditCardNumber(paymentRequest.getCreditCard().getCardNumber())
                .build();

        CreditCardValidationResponse validationResponse = restTemplate.postForObject("http://VALIDATION-SERVICE/creditcards/validate",
                validationRequest, CreditCardValidationResponse.class);

        // pay insurance policy amount
        if (validationResponse.isValid()) {
            // retrieve payment
            log.info("Payment retrieved successfully!");

            // save transaction to database
            insuranceRepository.save(Insurance.builder()
                    .customer(customer)
                    .vehicle(vehicle)
                    .insuranceAmount(paymentRequest.getInsuranceAmount())
                    .insuranceExpiryDate(LocalDate.now().plusYears(1))
                    .build());
            log.info("Insurance transaction saved to database successfully!");

            // notify customer about insurance policy
            NotificationRequest notificationRequest = NotificationRequest.builder()
                    .toCustomerId(customer.getId())
                    .message("Hello " + customer.getFullName() + ", Insurance policy successfully generated!")
                    .toCustomerPhone(customer.getPhoneNumber())
                    .build();

            restTemplate.postForObject("http://NOTIFICATION-SERVICE/notifications",
                    notificationRequest, Boolean.class);

        } else {
            String creditCardNumberAsStr = String.valueOf(validationRequest.getCreditCardNumber());
            throw new CreditCardValidationException(String.format("Credit card number is not valid : %s",
                    creditCardNumberAsStr.substring(0, 4) + "********" + creditCardNumberAsStr.substring(12, 16)));
        }

        // return result
        return InsurancePaymentResponse.builder()
                .customerID(paymentRequest.getCustomerID())
                .vehiclePlate(paymentRequest.getVehiclePlate())
                .insuranceAmount(paymentRequest.getInsuranceAmount())
                .dateOfExpiry(LocalDate.now().plusYears(1))
                .isPaid(Boolean.TRUE)
                .build();
    }
}
