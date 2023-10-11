package com.robotdreams.clients;

import com.robotdreams.controller.CustomerController;
import com.robotdreams.models.*;
import com.robotdreams.utils.EntityManagerUtils;
import com.robotdreams.utils.LogUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class InsuranceTestClient {
    private static EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");
    private static CustomerController customerController = new CustomerController();
    private static Customer newCustomer = new Customer("Mustafa Çiftçi", "Sarıyer Istanbul", 111111112L, "3143154351");

    public static void main(String[] args) {
        // checks if any data exists on DB or not
        if (checkData()) {
            System.out.println("Test data not exist! Will be persisted...");
            persistTestData();
        }

        // executes test methods
        findAllCustomer();
        persistNewCustomer();
        updateCustomer();
        findCustomer();
        deleteCustomer();
    }

    private static void deleteCustomer() {
        LogUtils.logMethodStart(new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        customerController.deleteCustomer(newCustomer);
    }

    private static void findCustomer() {
        LogUtils.logMethodStart(new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        System.out.println(customerController.findCustomer(newCustomer.getId()));
    }

    private static void updateCustomer() {
        LogUtils.logMethodStart(new Object(){}.getClass().getEnclosingMethod().getName());
        newCustomer.setAddress("Adana....");
        customerController.saveCustomer(newCustomer);
    }

    private static void persistNewCustomer() {
        LogUtils.logMethodStart(new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        customerController.saveCustomer(newCustomer);
        findAllCustomer();
    }

    private static void findAllCustomer() {
        LogUtils.logMethodStart(new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName());

        List<Customer> customerList = customerController.findAllCustomer();

        for (int i = 1; i <= customerList.size(); i++) {
            System.out.println(i + " --> " + customerList.get(i - 1));
        }
    }

    private static boolean checkData() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList().size() == 0;
    }

    private static void persistTestData() {
        System.out.println("Start....");

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

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(customer1);
            entityManager.persist(customer2);
            entityManager.persist(customer3);

            entityManager.persist(car1);
            entityManager.persist(car2);
            entityManager.persist(moto1);
            entityManager.persist(moto2);
            entityManager.persist(moto3);

            entityManager.persist(accident1);
            entityManager.persist(accident2);
            entityManager.persist(accident3);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtils.closeEntityManager(entityManager);
        }

        System.out.println("Finish....");
    }
}
