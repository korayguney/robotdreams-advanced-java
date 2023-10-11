package com.robotdreams.validationservice.controller;

import com.robotdreams.validationservice.model.CreditCardValidationRequest;
import com.robotdreams.validationservice.model.CreditCardValidationResponse;
import com.robotdreams.validationservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService service;

    @PostMapping("/creditcards/validate")
    public CreditCardValidationResponse validateCreditCard(@RequestBody CreditCardValidationRequest request) {
        return service.validateCreditCard(request);
    }


}
