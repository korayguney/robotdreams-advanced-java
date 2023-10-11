package com.robotdreams.insurance.model;

import jakarta.persistence.Entity;

@Entity
public class Motorcycle extends Vehicle {
    private double enginePower;

    public Motorcycle(double enginePower) {
        this.enginePower = enginePower;
    }

    public Motorcycle(String model, int year, String plate, double enginePower) {
        super(model, year, plate);
        this.enginePower = enginePower;
    }

    public Motorcycle() {
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Motorcycle that = (Motorcycle) o;

        return Double.compare(that.enginePower, enginePower) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(enginePower);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "enginePower=" + enginePower +
                "} " + super.toString();
    }
}
