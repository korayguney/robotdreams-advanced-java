package com.robotdreams.insurance.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

// POJO
@Entity
@Table(name = "customertable")
@Builder
@AllArgsConstructor
public class Customer {
    // variables & members & fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String address;
    private long ssidNumber;
    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    //@OneToMany(mappedBy = "customer")
    private List<Vehicle> vehicleList = new ArrayList<>();

    // constructors
    public Customer(){}

    public Customer(String fullName){
        this.fullName = fullName;
    }

    public Customer(String fullName, String address, long ssidNumber, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.ssidNumber = ssidNumber;
        this.phoneNumber = phoneNumber;
    }

    // custom methods

    // getters & setters
    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getSsidNumber() {
        return ssidNumber;
    }

    public void setSsidNumber(long ssidNumber) {
        this.ssidNumber = ssidNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    // toString & equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return ssidNumber == customer.ssidNumber;
    }

    @Override
    public int hashCode() {
        return (int) (ssidNumber ^ (ssidNumber >>> 32));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", ssidNumber=" + ssidNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
