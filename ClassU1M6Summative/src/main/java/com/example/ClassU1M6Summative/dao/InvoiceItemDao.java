package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    /**
     * Add a single invoice item object to the database.
     * .update() takes a sql or prepared sql statement for the first arg
     * and each property (omitting the invoice_item_id) for each additional arg.
     * We then, set the invoice_item_id by using query "select LAST_INSERT_ID()".
     * @param invoiceItem object
     * @return InvoiceItem object
     */
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    /**
     * Delete one InvoiceItem by a given item_id.
     * use .update() to pass prepared statement and item_id to sql for invoiceItem deletion
     * @param item_id type int
     */
    void deleteInvoiceItemByItemId(int item_id);

    /**
     * Delete one InvoiceItem by a given invoice_id.
     * use .update() to pass prepared statement and item_id to sql for invoiceItem deletion
     * @param invoice_id type int
     */
    void deleteInvoiceItemByInvoiceId(int invoice_id);

    void deleteInvoiceItem(int invoice_item_id);

    List<InvoiceItem> getAllInvoiceItems();

    List<InvoiceItem> getAllInvoiceItemsByInvoiceId(int invoice_id);

}
