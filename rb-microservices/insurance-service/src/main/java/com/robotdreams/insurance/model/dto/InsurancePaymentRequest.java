package com.robotdreams.insurance.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsurancePaymentRequest {
    private long customerID;
    private String vehiclePlate;
    private double insuranceAmount;
    private CreditCardDTO creditCard;
}
