package com.example.ClassU1M6Summative.controller;

import com.example.ClassU1M6Summative.dao.CustomerDao;
import com.example.ClassU1M6Summative.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerDao customerDao;

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomerById(@PathVariable (name="id") int id) {
        return customerDao.getCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer customer1 = new Customer();

        customer1.setFirst_name(customer.getFirst_name());
        customer1.setLast_name(customer.getLast_name());
        customer1.setCompany(customer.getCompany());
        customer1.setEmail(customer.getEmail());
        customer1.setPhone(customer.getPhone());

        customerDao.addCustomer(customer1);

        return customer1;
    }

    @RequestMapping(value="/customer/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable(name="id") int id) {
        Customer customer1 = customerDao.getCustomer(id);
        if (customer.getFirst_name() != null) {
            customer1.setFirst_name(customer.getFirst_name());
        }
        if (customer.getLast_name() != null) {
            customer1.setLast_name(customer.getLast_name());
        }
        if (customer.getCompany() != null) {
            customer1.setCompany(customer.getCompany());
        }
        if (customer.getEmail() != null) {
            customer1.setEmail(customer.getEmail());
        }
        if (customer.getPhone() != null) {
            customer1.setPhone(customer.getPhone());
        }
        customerDao.updateCustomer(customer1);
    }

    @RequestMapping(value="/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable(name = "id") int id) {
        customerDao.deleteCustomer(id);
    }
}
