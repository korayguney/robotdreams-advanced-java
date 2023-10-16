package com.robotdreams.insurance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private int year;
    private String plate;

    @JsonBackReference
    @ManyToOne
    private Customer customer;

    @JsonBackReference
    @ManyToMany
    private Set<Accident> accidents = new HashSet<>();

    public Vehicle() {
    }

    public Vehicle(String model, int year, String plate) {
        this.model = model;
        this.year = year;
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(Set<Accident> accidents) {
        this.accidents = accidents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        return plate != null ? plate.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", plate='" + plate + '\'' +
                '}';
    }
}
