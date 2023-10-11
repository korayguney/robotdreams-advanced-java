package com.robotdreams.initializer;

import com.robotdreams.model.*;
import com.robotdreams.repository.AccidentRepository;
import com.robotdreams.repository.CustomerRepository;
import com.robotdreams.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@Component
public class TestDataInitializer implements ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final AccidentRepository accidentRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public TestDataInitializer(CustomerRepository customerRepository, AccidentRepository accidentRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.accidentRepository = accidentRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Start....");

        if (customerRepository.findAll().size() == 0) {

            Customer customer1 = new Customer("Ali Veli", "Tuzla Istanbul", 111111111L, "123423242");
            Customer customer2 = new Customer("Ayşe Turk", "Baku ", 12345678L, "345324523523");
            Customer customer3 = new Customer("Hasan Simsek", "Bostancı Istanbul", 4444444L, "777654643563");

            Vehicle car1 = new Car("Hyundai Accent", 2020, "34VG4555", "yellow");
            Vehicle car2 = new Car("Honda Accord", 2018, "34VG111", "black");
            Vehicle moto1 = new Motorcycle("Yamaha", 2015, "06TY555", 222.55);
            Vehicle moto2 = new Motorcycle("Yamakazi", 2018, "35AA555", 145.3);
            Vehicle moto3 = new Motorcycle("Kawasaki", 2022, "01TY355", 250.44);

            car1.setCustomer(customer1);
            car2.setCustomer(customer2);
            moto1.setCustomer(customer1);
            moto2.setCustomer(customer3);
            moto3.setCustomer(customer2);

            Accident accident1 = new Accident(LocalDate.of(2022, Month.APRIL, 22));
            Accident accident2 = new Accident(LocalDate.of(2021, Month.AUGUST, 2));
            Accident accident3 = new Accident(LocalDate.of(2020, Month.JANUARY, 22));

            car1.getAccidents().add(accident1);
            car2.getAccidents().add(accident1);
            moto1.getAccidents().add(accident3);
            moto2.getAccidents().add(accident1);
            moto3.getAccidents().add(accident2);

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);

            vehicleRepository.save(car1);
            vehicleRepository.save(car2);
            vehicleRepository.save(moto1);
            vehicleRepository.save(moto2);
            vehicleRepository.save(moto3);

            accidentRepository.save(accident1);
            accidentRepository.save(accident2);
            accidentRepository.save(accident3);

            System.out.println("Start....");
        }
    }
}
