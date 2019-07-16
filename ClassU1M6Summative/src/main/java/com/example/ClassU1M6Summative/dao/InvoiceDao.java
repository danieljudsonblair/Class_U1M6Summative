package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int invoiceID);

    List<Invoice> getAllInvoices();

    List<Integer> getAllInvoiceIdsByCustomerId(int customer_id);

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int invoiceID);

}
