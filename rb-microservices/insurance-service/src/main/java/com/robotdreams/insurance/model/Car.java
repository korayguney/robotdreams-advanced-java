package com.robotdreams.insurance.model;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Car extends Vehicle {
    private String color;

    public Car(String color) {
        this.color = color;
    }

    public Car(String model, int year, String plate, String color) {
        super(model, year, plate);
        this.color = color;
    }

    public Car() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        return Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                "} " + super.toString();
    }
}
