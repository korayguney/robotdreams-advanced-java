package com.robotdreams.insurance.service;

import com.robotdreams.insurance.model.dto.InsurancePaymentRequest;
import com.robotdreams.insurance.model.dto.InsurancePaymentResponse;

public interface InsuranceService {
    InsurancePaymentResponse payForInsurancePolicy(InsurancePaymentRequest paymentRequest);
}
