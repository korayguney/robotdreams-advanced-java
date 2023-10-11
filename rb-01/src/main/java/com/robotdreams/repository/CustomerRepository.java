package com.robotdreams.repository;

import com.robotdreams.models.Customer;
import com.robotdreams.models.Vehicle;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer> {
    List<Vehicle> findAllVehicleOfCustomerWithSSID(long ssid);
}
