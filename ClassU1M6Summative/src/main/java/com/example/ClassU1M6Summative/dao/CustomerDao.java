package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer customer);

    Customer getCustomer(int customer_id);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
