package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Invoice;
import com.example.ClassU1M6Summative.model.InvoiceItem;
import com.example.ClassU1M6Summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        invoiceItemDao.getAllInvoiceItems().stream().forEach(ii -> invoiceItemDao.deleteInvoiceItem(ii.getInvoice_item_id()));
        invoiceDao.getAllInvoices().stream().forEach(i -> invoiceDao.deleteInvoice(i.getInvoiceID()));
        itemDao.getAllItems().stream().forEach(i -> itemDao.deleteItem(i.getItem_id()));
        customerDao.getAllCustomers().stream().forEach(c -> customerDao.deleteCustomer(c.getCustomer_id()));
    }

    @Test
    public void addGetDeleteCustomer() {

        Customer customer = new Customer();
        customer.setFirst_name("First");
        customer.setLast_name("Last");
        customer.setCompany("Company");
        customer.setEmail("customer@customer.com");
        customer.setPhone("555-555-5555");

        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2019, 7, 13));
        invoice.setPickupDate(LocalDate.of(2019, 7, 14));
        invoice.setReturnDate(LocalDate.of(2019, 8, 14));
        invoice.setLateFee(new BigDecimal("5.00"));

        invoiceDao.addInvoice(invoice);

        Item item = new Item();
        item.setName("Name");
        item.setDescription("Description");
        item.setDaily_rate(new BigDecimal("5.99"));

        itemDao.addItem(item);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(invoice.getInvoiceID());
        invoiceItem.setItem_id(item.getItem_id());
        invoiceItem.setQuantity(10);
        invoiceItem.setUnit_rate(new BigDecimal("3.75"));
        invoiceItem.setDiscount(new BigDecimal("1.00"));

        invoiceItemDao.addInvoiceItem(invoiceItem);

        Item item1 = new Item();
        item1.setName("New Name");
        item1.setDescription("New Description");
        item1.setDaily_rate(new BigDecimal("6.99"));

        itemDao.addItem(item1);

        customerDao.addCustomer(customer);

        assertEquals(customerDao.getCustomer(customer.getCustomer_id()), customer);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoice_item_id());
        invoiceDao.deleteInvoice(invoice.getInvoiceID());
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


