package com.example.ClassU1M6Summative.dao;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvoiceItemDaoTest {

    // Dependency Injection
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    CustomerDao customerDao;
    // @Autowired
    // ItemDao itemDao;
    // @Autowired
    // InvoiceDao invoiceDao;

    @Before
    public void setUp()throws Exception{
        // CLEAR DBs IN PROPER ORDER

        // Delete invoiceItemDao
        // Delete itemDao
        // Delete invoiceDao
        // Delete customerDao
    }

    @Test
    public void addInvoiceItemTest(){
        // Create Customer
        // Create Item
        // Create Invoice
        // Create Invoice Item
    }

    @Test
    public void deleteInvoiceItemByItemId(){

    }

    @Test
    public void deleteInvoiceItemByInvoiceId(){

    }
}
