package com.robotdreams.repository;

import com.robotdreams.model.Accident;
import com.robotdreams.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {
}
