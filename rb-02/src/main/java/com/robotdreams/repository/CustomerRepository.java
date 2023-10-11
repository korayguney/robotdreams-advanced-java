package com.robotdreams.repository;

import com.robotdreams.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //@Query("SELECT c FROM Customer c WHERE c.ssidNumber = :ssid")
    @Query(value = "SELECT * FROM customertable c WHERE c.ssid_number = ?1" , nativeQuery = true)
    Customer findCustomerBySsid(long ssid);

    // Method-driven query
    Customer findCustomerByPhoneNumber(String phoneNumber);
    Customer findCustomerByAddressAndSsidNumber(String address, long ssid);

}
