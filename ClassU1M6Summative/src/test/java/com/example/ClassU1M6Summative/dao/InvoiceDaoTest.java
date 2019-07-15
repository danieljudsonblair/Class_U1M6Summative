package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Invoice;
import com.example.ClassU1M6Summative.model.InvoiceItem;
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
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        invoiceItemDao.getAllInvoiceItems().stream().forEach(ii -> invoiceItemDao.deleteInvoiceItem(ii.getInvoice_item_id()));
        itemDao.getAllItems().stream().forEach(item -> itemDao.deleteItem(item.getItem_id()));
        invoiceDao.getAllInvoices().stream().forEach(c -> invoiceDao.deleteInvoice(c.getInvoiceID()));
        customerDao.getAllCustomers().stream().forEach(c -> customerDao.deleteCustomer(c.getCustomer_id()));

    }

    @Test
    public void addGetDeleteInvoice() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal("6.55"));
        invoice = invoiceDao.addInvoice(invoice);

        assertEquals(1, invoiceDao.getAllInvoices().size());
        invoiceDao.deleteInvoice(invoice.getInvoiceID());
        assertNull(invoiceDao.getInvoice(invoice.getInvoiceID()));
    }

    @Test
    public void updateInvoice() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal("6.55"));
        invoice = invoiceDao.addInvoice(invoice);

        invoice.setCustomerID(invoice.getCustomerID());
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal("10"));

        invoiceDao.updateInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoiceID());

        assertEquals(invoice2.getInvoiceID(), invoice.getInvoiceID());
    }

    @Test
    public void getAllInvoice() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setEmail("customer@customer.com");
        customer.setCompany("company");
        customer.setPhone("555-555-5555");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal("6.55"));

        invoiceDao.addInvoice(invoice);

        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2018, 6, 3));
        invoice.setPickupDate(LocalDate.of(2018, 6, 5));
        invoice.setReturnDate(LocalDate.of(2018, 6, 7));
        invoice.setLateFee(new BigDecimal("10.55"));

        invoiceDao.addInvoice(invoice);

        assertEquals(invoiceDao.getAllInvoices().size(), 2);
    }
}


