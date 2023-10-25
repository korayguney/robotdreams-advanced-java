package com.robotdreams.insurance.service.impl;

import com.robotdreams.clients.notification.NotificationClient;
import com.robotdreams.clients.notification.NotificationRequest;
import com.robotdreams.clients.validation.CreditCardValidationClient;
import com.robotdreams.clients.validation.CreditCardValidationRequest;
import com.robotdreams.clients.validation.CreditCardValidationResponse;
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
import com.robotdreams.rabbitmq.RabbitMQMessageProducer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class InsuranceServiceImpl implements InsuranceService {

    @Value("${rabbitmq.exchanges.internal}")
    private String notificationExchange;

    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String notificationRoutingKey;

    private final InsuranceRepository insuranceRepository;
    private final CustomerRepository customerRepository; // Not recommended!
    private final CustomerService customerService;
    private final VehicleService vehicleService;
    private final RestTemplate restTemplate;
    private final CreditCardValidationClient creditCardValidationClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer mqMessageProducer;

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

       // CreditCardValidationResponse validationResponse = restTemplate.postForObject("http://VALIDATION-SERVICE/creditcards/validate",
       //         validationRequest, CreditCardValidationResponse.class);

        CreditCardValidationResponse validationResponse = creditCardValidationClient.validateCreditCard(validationRequest);

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

           //restTemplate.postForObject("http://NOTIFICATION-SERVICE/notifications",
           //        notificationRequest, Boolean.class);
            //notificationClient.sendNotification(notificationRequest);
            mqMessageProducer.publish(notificationRequest,
                    notificationExchange,
                    notificationRoutingKey);

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
