package com.example.ClassU1M6Summative.service;

import com.example.ClassU1M6Summative.dao.*;
import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Invoice;
import com.example.ClassU1M6Summative.model.InvoiceItem;
import com.example.ClassU1M6Summative.model.Item;
import com.example.ClassU1M6Summative.viewmodel.CustomerViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();

        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }

    @Test
    public void saveCustomer(){
        CustomerViewModel cvm = new CustomerViewModel();
        Customer customer = new Customer();
        cvm.setCustomer(customer);
        customer.setFirst_name("Sara");
        customer.setLast_name("Cole");
        customer.setPhone("919.441.3172");
        customer.setCompany("Verizon");
        customer.setEmail("sara@verizon.com");
        customer = service.saveCustomer(customer);
        cvm.setCustomer(customer);
        
    }

    // Helper Methods
    private void setUpCustomerDaoMock(){
    customerDao = mock(CustomerDaoJdbcTemplateImpl.class);

        Customer customer = new Customer();
        customer.setEmail("email");
        customer.setCompany("company");
        customer.setPhone("phone");
        customer.setFirst_name("first");
        customer.setLast_name("last");
        customer.setCustomer_id(1);

        Customer customer1 = new Customer();
        customer.setEmail("email");
        customer.setCompany("company");
        customer.setPhone("phone");
        customer.setFirst_name("first");
        customer.setLast_name("last");
        customer.setCustomer_id(1);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).addCustomer(customer1);
        doReturn(customer).when(customerDao).getCustomer(customer.getCustomer_id());
        doReturn(customerList).when(customerDao).getAllCustomers();
    }

    private void setUpInvoiceDaoMock(){
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(1);
        invoice.setCustomerID(4);
        invoice.setLateFee(new BigDecimal("9.99"));
        invoice.setOrderDate(LocalDate.of(2019, 2, 14));
        invoice.setPickupDate(LocalDate.of(2019, 2, 14));
        invoice.setReturnDate(LocalDate.of(2019, 3, 14));

        Invoice invoice1 = new Invoice();
        invoice.setCustomerID(4);
        invoice.setLateFee(new BigDecimal("9.99"));
        invoice.setOrderDate(LocalDate.of(2019, 2, 14));
        invoice.setPickupDate(LocalDate.of(2019, 2, 14));
        invoice.setReturnDate(LocalDate.of(2019, 3, 14));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        List<Integer> invoiceIdList = new ArrayList<>();
        invoiceIdList.add(invoice.getInvoiceID());

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(10);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
        doReturn(invoiceList).when(invoiceDao).getAllInvoicesByCustomerId(4);
        doReturn(invoiceIdList).when(invoiceDao).getAllInvoiceIdsByCustomerId(4);
    }

    private void setUpInvoiceItemDaoMock(){
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(2);
        invoiceItem.setInvoice_id(1);
        invoiceItem.setItem_id(3);
        invoiceItem.setQuantity(10);
        invoiceItem.setDiscount(new BigDecimal("5.5"));
        invoiceItem.setUnit_rate(new BigDecimal("6.5"));

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem.setInvoice_item_id(2);
        invoiceItem.setInvoice_id(1);
        invoiceItem.setItem_id(3);
        invoiceItem.setQuantity(10);
        invoiceItem.setDiscount(new BigDecimal("5.5"));
        invoiceItem.setUnit_rate(new BigDecimal("6.5"));

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem1);
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItems();
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItemsByInvoiceId(1);
    }

    private void setUpItemDaoMock(){
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item();
        item.setItem_id(1);
        item.setName("Washing Machine");
        item.setDescription("Washes clothing.");
        item.setDaily_rate(new BigDecimal("15.99"));

        Item item2 = new Item();
        item.setName("Washing Machine");
        item.setDescription("Washes clothing.");
        item.setDaily_rate(new BigDecimal("15.99"));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(1);
        doReturn(itemList).when(itemDao).getAllItems();
    }
}
