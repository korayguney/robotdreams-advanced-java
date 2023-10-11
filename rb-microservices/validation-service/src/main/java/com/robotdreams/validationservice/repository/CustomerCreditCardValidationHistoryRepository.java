package com.robotdreams.validationservice.repository;

import com.robotdreams.validationservice.model.CustomerCreditCardValidationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCreditCardValidationHistoryRepository extends JpaRepository<CustomerCreditCardValidationHistory, Long> {
}