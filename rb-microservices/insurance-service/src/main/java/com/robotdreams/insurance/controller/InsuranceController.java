package com.robotdreams.insurance.controller;

import com.robotdreams.insurance.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.robotdreams.insurance.model.dto.InsurancePaymentRequest;
import com.robotdreams.insurance.model.dto.InsurancePaymentResponse;

@RestController
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping("/insurances/payment")
    public InsurancePaymentResponse payForInsurancePolicy(@RequestBody InsurancePaymentRequest paymentRequest) {
        return insuranceService.payForInsurancePolicy(paymentRequest);
    }

}
