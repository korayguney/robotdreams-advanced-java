package com.robotdreams.validationservice.service;


import com.robotdreams.validationservice.model.CreditCardValidationRequest;
import com.robotdreams.validationservice.model.CreditCardValidationResponse;
import com.robotdreams.validationservice.model.CustomerCreditCardValidationHistory;
import com.robotdreams.validationservice.repository.CustomerCreditCardValidationHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardService {

    private final CustomerCreditCardValidationHistoryRepository repository;

    @Transactional
    public CreditCardValidationResponse validateCreditCard(CreditCardValidationRequest request) {
        boolean isValid = checkCreditCardNumber(request.getCreditCardNumber());
        CustomerCreditCardValidationHistory history = CustomerCreditCardValidationHistory.builder()
                .customerId(request.getCustomerId())
                .binNumber(Integer.valueOf(String.valueOf(request.getCreditCardNumber()).substring(0,6)))
                .isValid(isValid)
                .validationDate(LocalDateTime.now())
                .build();
        repository.save(history);
        log.info("Valdaiton request accepted : {} ", request.getCustomerId());
        return new CreditCardValidationResponse(isValid);
    }

    private boolean checkCreditCardNumber(long creditCardNumber) {
        String cardNumber = String.valueOf(creditCardNumber);
        int nDigits = cardNumber.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = cardNumber.charAt(i) - '0';
            if (isSecond)
                d = d * 2;
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        boolean result = nSum % 10 == 0;
        return result;
    }


}
