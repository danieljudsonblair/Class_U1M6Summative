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
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItems();
        invoiceItems.stream().forEach(i -> invoiceItemDao.deleteInvoiceItem(i.getInvoice_item_id()));

        List<Item> items = itemDao.getAllItems();
        items.stream().forEach(item -> itemDao.deleteItem(item.getItem_id()));

        List<Invoice> invoices = invoiceDao.getAllInvoices();
        invoices.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceID()));

        List<Customer> customers = customerDao.getAllCustomers();
        customers.stream().forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));
    }

    @Test
    public void addInvoiceItemTest() {
        // Create Customer
        Customer customer = new Customer();
        customer.setFirst_name("Evan");
        customer.setLast_name("Wilson");
        customer.setEmail("customer@email.com");
        customer.setCompany("Biznass");
        customer.setPhone("555-555-0000");
        customer = customerDao.addCustomer(customer);

        // Create Item
        Item item = new Item();
        item.setName("Wings");
        item.setDescription("This is a backpack type apparatus that is for decoration only");
        item.setDaily_rate(new BigDecimal(121.55));
        item = itemDao.addItem(item);

        // Create Invoice
        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2019, 01, 01));
        invoice.setPickupDate(LocalDate.of(2019, 02, 02));
        invoice.setReturnDate(LocalDate.of(2019, 03, 03));
        invoice.setLateFee(new BigDecimal(9.99));
        invoice = invoiceDao.addInvoice(invoice);

        // Create Invoice Item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(invoice.getInvoiceID());
        invoiceItem.setItem_id(item.getItem_id());
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_rate(new BigDecimal(15.99));
        invoiceItem.setDiscount(new BigDecimal(5.00));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);


        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());
    }

    @Test
    public void deleteInvoiceItemByItemIdTest() {
        // Create Customer
        Customer customer = new Customer();
        customer.setFirst_name("Evan");
        customer.setLast_name("Wilson");
        customer.setEmail("customer@email.com");
        customer.setCompany("Biznass");
        customer.setPhone("555-555-0000");
        customer = customerDao.addCustomer(customer);

        // Create Item
        Item item = new Item();
        item.setName("Wings");
        item.setDescription("This is a backpack type apparatus that is for decoration only");
        item.setDaily_rate(new BigDecimal(121.55));
        item = itemDao.addItem(item);

        Item item2 = new Item();
        item2.setName("item2");
        item2.setDescription("This is a item 2");
        item2.setDaily_rate(new BigDecimal(555.55));
        item2 = itemDao.addItem(item2);

        // Create Invoice
        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2019, 01, 01));
        invoice.setPickupDate(LocalDate.of(2019, 02, 02));
        invoice.setReturnDate(LocalDate.of(2019, 03, 03));
        invoice.setLateFee(new BigDecimal(9.99));
        invoice = invoiceDao.addInvoice(invoice);

        // Create Invoice Item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(invoice.getInvoiceID());
        invoiceItem.setItem_id(item.getItem_id());
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_rate(new BigDecimal(15.99));
        invoiceItem.setDiscount(new BigDecimal(5.00));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(invoice.getInvoiceID());
        invoiceItem.setItem_id(item2.getItem_id());
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_rate(new BigDecimal(15.99));
        invoiceItem.setDiscount(new BigDecimal(5.00));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItemDao.deleteInvoiceItemByItemId(item.getItem_id());

        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());

    }

    @Test
    public void deleteInvoiceItemByInvoiceIdTest() {
        // Create Customer
        Customer customer = new Customer();
        customer.setFirst_name("Evan");
        customer.setLast_name("Wilson");
        customer.setEmail("customer@email.com");
        customer.setCompany("Biznass");
        customer.setPhone("555-555-0000");
        customer = customerDao.addCustomer(customer);

        // Create Item
        Item item = new Item();
        item.setName("Wings");
        item.setDescription("This is a backpack type apparatus that is for decoration only");
        item.setDaily_rate(new BigDecimal(121.55));
        item = itemDao.addItem(item);


        // Create 2 invoices
        Invoice invoice = new Invoice();
        invoice.setCustomerID(customer.getCustomer_id());
        invoice.setOrderDate(LocalDate.of(2019, 01, 01));
        invoice.setPickupDate(LocalDate.of(2019, 02, 02));
        invoice.setReturnDate(LocalDate.of(2019, 03, 03));
        invoice.setLateFee(new BigDecimal(9.99));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerID(customer.getCustomer_id());
        invoice2.setOrderDate(LocalDate.of(2019, 01, 01));
        invoice2.setPickupDate(LocalDate.of(2019, 02, 02));
        invoice2.setReturnDate(LocalDate.of(2019, 03, 03));
        invoice2.setLateFee(new BigDecimal(9.99));
        invoice2 = invoiceDao.addInvoice(invoice2);

        // Create 2 Invoice Item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(invoice.getInvoiceID());
        invoiceItem.setItem_id(item.getItem_id());
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_rate(new BigDecimal(15.99));
        invoiceItem.setDiscount(new BigDecimal(5.00));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_id(invoice2.getInvoiceID());
        invoiceItem.setItem_id(item.getItem_id());
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_rate(new BigDecimal(15.99));
        invoiceItem.setDiscount(new BigDecimal(5.00));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        // Delete one invoice_item by invoice_id leaving one in the db
        invoiceItemDao.deleteInvoiceItemByInvoiceId(invoice.getInvoiceID());

        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());
    }
}
