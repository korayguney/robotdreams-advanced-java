package com.robotdreams.rbfinalproject.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class CurrencyModel {
    private List<CurrencySubModel> currencies = new ArrayList<>();
}