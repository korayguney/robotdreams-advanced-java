package com.robotdreams.rbfinalproject.repository;

import com.robotdreams.rbfinalproject.model.ConversionEntity;
import com.robotdreams.rbfinalproject.model.ConversionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {

    ConversionEntity findByTransactionId(String transactionId);

}
