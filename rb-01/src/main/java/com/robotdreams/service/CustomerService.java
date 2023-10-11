package com.robotdreams.service;

import com.robotdreams.models.Customer;
import com.robotdreams.models.Vehicle;
import com.robotdreams.repository.CustomerRepository;
import com.robotdreams.utils.EntityManagerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


import java.util.List;

public class CustomerService implements CustomerRepository {

    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

    @Override
    public List<Customer> findAll() {
        // JPQL & HQL
        if(!em.isOpen()) {
            em = EntityManagerUtils.getEntityManager("mysqlPU");
        }
        // select * from customertable;
        return em.createQuery("FROM Customer", Customer.class).getResultList();
        // em.createNativeQuery("select * from customertable", Customer.class).getResultList();
    }

    @Override
    public Customer findById(long id) {
        if(!em.isOpen()) {
            em = EntityManagerUtils.getEntityManager("mysqlPU");
        }
        // select * from customertable where id=?
        Customer customer = em.find(Customer.class, id);
        return customer;
    }

    @Override
    public void saveToDatabase(Customer customer) {
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            System.out.println("Customer saved...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteFromDatabase(Customer customer) {
        try {
            em.getTransaction().begin();
            em.remove(customer);
            em.getTransaction().commit();
            System.out.println("Customer deleted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteFromDatabase(int id) {

    }

    @Override
    public void updateOnDatabase(Customer customer) {

    }

    @Override
    public List<Vehicle> findAllVehicleOfCustomerWithSSID(long ssid) {
        TypedQuery<Customer> q =  em.createQuery("FROM Customer c WHERE c.ssid = :ssid", Customer.class);
        q.setParameter("ssid", ssid);
        Customer customer = q.getSingleResult(); // Lazy loading
      //  Customer customer = em.createQuery("FROM Customer c WHERE c.ssid = :ssid", Customer.class).setParameter("ssid", ssid).getSingleResult();
        return customer.getVehicleList();
    }
}
