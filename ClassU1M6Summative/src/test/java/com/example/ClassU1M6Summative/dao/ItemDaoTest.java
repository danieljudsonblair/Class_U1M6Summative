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
public class ItemDaoTest {

    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
       invoiceItemDao.getAllInvoiceItems().stream().forEach(ii -> invoiceItemDao.deleteInvoiceItem(ii.getInvoice_item_id()));
       invoiceDao.getAllInvoices().stream().forEach(i -> invoiceDao.deleteInvoice(i.getInvoiceID()));
       itemDao.getAllItems().stream().forEach(i -> itemDao.deleteItem(i.getItem_id()));
       customerDao.getAllCustomers().stream().forEach(c -> customerDao.deleteCustomer(c.getCustomer_id()));
    }

    @Test
    public void addGetDeleteItem() {

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

        assertEquals(itemDao.getItem(item.getItem_id()), item);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoice_item_id());

        itemDao.deleteItem(item.getItem_id());

        assertNull(itemDao.getItem(item.getItem_id()));
    }

    @Test
    public void updateItem() {

        Item item = new Item();
        item.setName("Name");
        item.setDescription("Description");
        item.setDaily_rate(new BigDecimal("1.00"));

        itemDao.addItem(item);

        item.setName("Updated Name");
        item.setDescription("Updated Description");
        item.setDaily_rate(new BigDecimal("2.00"));

        itemDao.updateItem(item);

        assertEquals(itemDao.getItem(item.getItem_id()), item);
    }

    @Test
    public void getAllItems() {

        Item item = new Item();
        item.setName("Name");
        item.setDescription("Description");
        item.setDaily_rate(new BigDecimal("1.00"));

        itemDao.addItem(item);

        item.setName("New Name");
        item.setDescription("New Description");
        item.setDaily_rate(new BigDecimal("1.99"));

        itemDao.addItem(item);

        assertEquals(itemDao.getAllItems().size(), 2);
    }
}
