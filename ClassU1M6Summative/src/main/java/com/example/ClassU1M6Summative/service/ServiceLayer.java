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
        return cvm;
    }
}
