package com.robotdreams.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate accidentDate;

    @ManyToMany(mappedBy = "accidents")
    private Set<Vehicle> vehicleList = new HashSet<>();

    public Accident(LocalDate accidentDate) {
        this.accidentDate = accidentDate;
    }

    public Accident() {
    }

    public LocalDate getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(LocalDate accidentDate) {
        this.accidentDate = accidentDate;
    }

    public Set<Vehicle> getVehicleList() {
        return vehicleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accident accident = (Accident) o;

        return Objects.equals(accidentDate, accident.accidentDate);
    }

    @Override
    public int hashCode() {
        return accidentDate != null ? accidentDate.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "accidentDate=" + accidentDate +
                '}';
    }

}
