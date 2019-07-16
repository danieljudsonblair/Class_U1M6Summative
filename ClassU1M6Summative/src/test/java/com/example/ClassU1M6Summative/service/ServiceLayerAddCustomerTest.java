package com.example.ClassU1M6Summative.service;

import com.example.ClassU1M6Summative.dao.*;
import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Item;
import com.example.ClassU1M6Summative.viewmodel.CustomerViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerAddCustomerTest {

    ServiceLayer serviceLayer;
    CustomerDao customerDao;
    ItemDao itemDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception{
        setUpCustomerDaoMock();

        serviceLayer = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }

    @Test
    public void addCustomerTest(){

        CustomerViewModel cvm = new CustomerViewModel();

        Customer customer2 = new Customer();
        customer2.setFirst_name("First");
        customer2.setLast_name("Last");
        customer2.setCompany("Company");
        customer2.setEmail("customer2@customer2.com");
        customer2.setPhone("555-555-5555");

    }

    private void setUpCustomerDaoMock(){
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setCompany("Company");
        customer.setEmail("customer@customer.com");
        customer.setPhone("555-555-5555");

        Customer customer2 = new Customer();
        customer2.setFirst_name("First");
        customer2.setLast_name("Last");
        customer2.setCompany("Company");
        customer2.setEmail("customer2@customer2.com");
        customer2.setPhone("555-555-5555");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).addCustomer(customer2);
    }

}
