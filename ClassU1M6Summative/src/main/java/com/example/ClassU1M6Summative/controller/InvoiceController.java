package com.example.ClassU1M6Summative.controller;

import com.example.ClassU1M6Summative.dao.CustomerDao;
import com.example.ClassU1M6Summative.dao.InvoiceDao;
import com.example.ClassU1M6Summative.dao.InvoiceItemDao;
import com.example.ClassU1M6Summative.dao.ItemDao;
import com.example.ClassU1M6Summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ItemDao itemDao;

    /**
     * CREATE  an invoice object by first designating a customer to it using a path variable.
     * Then, create an Invoice object using the path variable for customer id.
     * @param invoice type Invoice
     * @param id type int (customer_id)
     * @return Invoice object
     */
    @RequestMapping(value = "/invoice/customer?{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice, @PathVariable(name = "id") int id) {
        Invoice invoice1 = new Invoice();
        invoice1.setCustomerID(id);
        invoice1.setOrderDate(LocalDate.of(2019, 07, 13));
        invoice1.setPickupDate(LocalDate.of(2019, 07, 14));
        invoice1.setReturnDate(LocalDate.of(2019, 07, 15));
        invoice1.setLateFee(new BigDecimal("11.99"));
        invoiceDao.addInvoice(invoice1);
        return invoice1;
    }

    /**
     * DELETE invoices by deleting invoice_items first, then deleting invoices.
     * Pass in customer id from path variable to access all invoices of a given
     * customer. Store those IDs in list and iterate though them executing
     * `deleteInvoiceItemByCustomerId()` on each one. Finally execute `deleteInvoice()`
     * on each.
     * @param id type int (customer_id)
     */
    @RequestMapping(value = "/invoice/customer?{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable(name = "id") int id) {
        List<Integer> invoices = invoiceDao.getAllInvoiceIdsByCustomerId(id);
        invoices.stream()
                .forEach(inv -> invoiceItemDao.deleteInvoiceItemByInvoiceId(inv));

        invoices.stream()
                .forEach(inv ->invoiceDao.deleteInvoice(inv));
    }

}
