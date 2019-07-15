package com.example.ClassU1M6Summative.dao;


import com.example.ClassU1M6Summative.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvoiceItemDaoTest {

    // Dependency Injection
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        // CLEAR DB IN PROPER ORDER
        // Delete invoiceItemDao
        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItems();

        // Delete itemDao
        // Delete invoiceDao
        // Delete customerDao
    }

    @Test
    public void addInvoiceItemTest() {
        // Create Customer
        // Create Item
        // Create Invoice
        // Create Invoice Item
    }

    @Test
    public void deleteInvoiceItemByItemIdTest() {

    }

    @Test
    public void deleteInvoiceItemByInvoiceIdTest() {

    }
}
