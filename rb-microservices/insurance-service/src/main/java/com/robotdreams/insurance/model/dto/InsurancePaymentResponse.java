package com.robotdreams.insurance.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InsurancePaymentResponse {
    private long customerID;
    private String vehiclePlate;
    private double insuranceAmount;
    private LocalDate dateOfExpiry;
    private boolean isPaid;
}
