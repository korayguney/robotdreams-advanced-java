package com.robotdreams.insurance.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardDTO {
    private String cardHolder;
    private long cardNumber;
    private int cardCVV;
    private String cardDateOfExpire;
}
