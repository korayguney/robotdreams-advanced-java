package com.robotdreams.insurance.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardValidationRequest {
    private long customerId;
    private long creditCardNumber;
}
