package com.example.ClassU1M6Summative.viewmodel;

import com.example.ClassU1M6Summative.model.Customer;
import com.example.ClassU1M6Summative.model.Invoice;
import com.example.ClassU1M6Summative.model.InvoiceItem;
import com.example.ClassU1M6Summative.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerViewModel {
    private Customer customer;
    private List<Invoice> invoices = new ArrayList<>();
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerViewModel that = (CustomerViewModel) o;
        return getCustomer().equals(that.getCustomer()) &&
                getInvoices().equals(that.getInvoices()) &&
                getInvoiceItems().equals(that.getInvoiceItems()) &&
                getItems().equals(that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getInvoices(), getInvoiceItems(), getItems());
    }
}
