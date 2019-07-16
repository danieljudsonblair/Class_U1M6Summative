package com.example.ClassU1M6Summative.service;

import com.example.ClassU1M6Summative.dao.CustomerDao;
import com.example.ClassU1M6Summative.dao.InvoiceDao;
import com.example.ClassU1M6Summative.dao.InvoiceItemDao;
import com.example.ClassU1M6Summative.dao.ItemDao;
import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Invoice;
import com.example.ClassU1M6Summative.model.InvoiceItem;
import com.example.ClassU1M6Summative.model.Item;
import com.example.ClassU1M6Summative.viewmodel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceLayer {
    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao, ItemDao itemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    // Customer API

    @Transactional
    public CustomerViewModel saveCustomer(CustomerViewModel cvm){
        // Persist customer
        Customer c = new Customer();
        c.setFirst_name(cvm.getCustomer().getFirst_name());
        c.setLast_name(cvm.getCustomer().getLast_name());
        c.setEmail(cvm.getCustomer().getEmail());
        c.setCompany(cvm.getCustomer().getCompany());
        c.setPhone(cvm.getCustomer().getPhone());
        c = customerDao.addCustomer(c);
        cvm.setId(c.getCustomer_id());

        List<Item> itemList = cvm.getItems();

        List<Invoice> listList = cvm.getInvoices();
        listList.stream()
            . forEach(invoice -> {
                invoice.setCustomerID(cvm.getId());
                invoiceDao.addInvoice(invoice);


                List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoiceId(invoice.getInvoiceID());
                invoiceItems.stream()
                                    .forEach(invoiceItem -> {
                                        itemList.stream()
                                                .forEach(item -> invoiceItem.setItem_id(item.getItem_id()));
                                        invoiceItem.setInvoice_id(invoice.getInvoiceID());
                                        invoiceItemDao.addInvoiceItem(invoiceItem);
                                    });
            });


        return cvm;
    }

    public List<CustomerViewModel> findAllCustomers() {

        List<Customer> customerList = customerDao.getAllCustomers();
        List<CustomerViewModel> cvmList = new ArrayList<>();

        for (Customer customer : customerList) {
            CustomerViewModel cvm = buildCustomerViewModel(customer);
            cvmList.add(cvm);
        }

        return cvmList;
    }



    public Invoice saveInvoice(Invoice invoice){
        return invoiceDao.addInvoice(invoice);
    }
    public Invoice findInvoice(int id){
        return invoiceDao.getInvoice(id);
    }
    public List<Invoice> findAllInvoices(){
        return invoiceDao.getAllInvoices();
    }
    public void updateInvoice(Invoice invoice){
        invoiceDao.updateInvoice(invoice);
    }
    public void deleteInvoice(int id){
        invoiceDao.deleteInvoice(id);
    }

    public Item saveItem(Item invoice){
        return itemDao.addItem(invoice);
    }
    public Item findItem(int id){
        return itemDao.getItem(id);
    }
    public List<Item> findAllItems(){
        return itemDao.getAllItems();
    }
    public void updateItem(Item invoice){
        itemDao.updateItem(invoice);
    }
    public void deleteItem(int id){
        itemDao.deleteItem(id);
    }

    public InvoiceItem saveInvoiceItem(InvoiceItem invoice){
        return invoiceItemDao.addInvoiceItem(invoice);
    }
    public List<InvoiceItem> findAllInvoiceItems(){
        return invoiceItemDao.getAllInvoiceItems();
    }
    public void deleteInvoiceItem(int id){
        invoiceItemDao.deleteInvoiceItem(id);
    }



    @Transactional
    public void updateCustomer(CustomerViewModel cvm) {
        Customer c = new Customer();

        customerDao.updateCustomer(c);

    }

    @Transactional
    public void removeCustomer(int invoice_id) {
    }

    // Invoice API


    // InvoiceItem API
    // Item API

    // Helper Method
    private CustomerViewModel buildCustomerViewModel(Customer customer){
        List<Invoice> custInvList = invoiceDao.getAllInvoicesByCustomerId(customer.getCustomer_id());

        List<List<InvoiceItem>> IiList = new ArrayList<>();
        for (Invoice inv : custInvList) {
            IiList.add(invoiceItemDao.getAllInvoiceItemsByInvoiceId(inv.getInvoiceID()));
        }
        List<Item> itemList = new ArrayList<>();
        for (List<InvoiceItem> iiL : IiList) {
            for(InvoiceItem ii : iiL) {
              itemList.add(itemDao.getItem(ii.getItem_id()));
            }
        }

        CustomerViewModel cvm = new CustomerViewModel();
        cvm.setId(customer.getCustomer_id());
        cvm.setCustomer(customer);
        cvm.setInvoices(custInvList);
        cvm.setInvoiceItems(IiList);
        cvm.setItems(itemList);
        return cvm;
    }
}
