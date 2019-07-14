package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        customerDao.getAllCustomers().stream().forEach(c -> customerDao.deleteCustomer(c.getCustomer_id()));
    }

    @Test
    public void addGetDeleteCustomer() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");

        customerDao.addCustomer(customer);

        assertEquals(customerDao.getCustomer(customer.getCustomer_id()), customer);

        customerDao.deleteCustomer(customer.getCustomer_id());

        assertNull(customerDao.getCustomer(customer.getCustomer_id()));
    }

    @Test
    public void updateCustomer() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");

        customerDao.addCustomer(customer);

        customer.setFirst_name("Updated First");
        customer.setLast_name("Updated Last");
        customer.setEmail("updatedCustomer@customer.com");
        customer.setCompany("updated company");
        customer.setPhone("111-111-1111");

        customerDao.updateCustomer(customer);

        assertEquals(customerDao.getCustomer(customer.getCustomer_id()), customer);
    }

    @Test
    public void getAllCustomers() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");

        customerDao.addCustomer(customer);

        customer.setFirst_name("New First");
        customer.setLast_name("New Last");
        customer.setEmail("newCustomer@customer.com");
        customer.setCompany("new company");
        customer.setPhone("222-222-2222");

        customerDao.addCustomer(customer);

        assertEquals(customerDao.getAllCustomers().size(), 2);
    }
}


