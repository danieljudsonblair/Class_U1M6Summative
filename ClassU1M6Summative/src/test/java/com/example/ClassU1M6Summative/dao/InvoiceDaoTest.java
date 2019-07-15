package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        invoiceDao.getAllInvoices().stream().forEach(c -> invoiceDao.deleteInvoice(c.getInvoiceID()));
        customerDao.getAllCustomers().stream().forEach(c -> customerDao.deleteCustomer(c.getCustomer_id()));

    }

    @Test
    public void addGetDeleteInvoice() {

        Invoice invoice = new Invoice();
        invoice.setCustomerID(1234);
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal(6.55));

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");

        invoiceDao.addInvoice(invoice);
        assertEquals(invoiceDao.getInvoice(invoice.getInvoiceID()), invoice);
        invoiceDao.deleteInvoice(invoice.getInvoiceID());
        assertNull(invoiceDao.getInvoice(invoice.getInvoiceID()));
        customerDao.addCustomer(customer);
        assertEquals(customerDao.getCustomer(customer.getCustomer_id()), customer);
        customerDao.deleteCustomer(customer.getCustomer_id());
        assertNull(customerDao.getCustomer(customer.getCustomer_id()));
    }
//comment
    @Test
    public void updateInvoice() {

        Invoice invoice = new Invoice();
        invoice.setCustomerID(1234);
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal(6.55));

        invoiceDao.addInvoice(invoice);

        invoice.setCustomerID(1234);
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal(6.55));

        invoiceDao.updateInvoice(invoice);

        assertEquals(invoiceDao.getInvoice(invoice.getInvoiceID()), invoice);
    }

    @Test
    public void getAllInvoice() {

        Invoice invoice = new Invoice();
        invoice.setCustomerID(1234);
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal(6.55));

        invoiceDao.addInvoice(invoice);

        invoice.setCustomerID(1234);
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal(6.55));

        invoiceDao.addInvoice(invoice);

        assertEquals(invoiceDao.getAllInvoices().size(), 2);
    }
}


