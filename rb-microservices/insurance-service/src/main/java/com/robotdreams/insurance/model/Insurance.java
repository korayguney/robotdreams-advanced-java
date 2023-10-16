package com.robotdreams.insurance.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private double insuranceAmount;
    private LocalDate insuranceExpiryDate;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Vehicle vehicle;
}
