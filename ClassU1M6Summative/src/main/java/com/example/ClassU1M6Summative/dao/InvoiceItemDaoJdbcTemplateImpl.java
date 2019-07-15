package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {

    // Init jdbc
    private JdbcTemplate jdbcTemplate;

    // Prepared sql statements
    private static final String INSERT_INVOICE_ITEM_SQL =
            "insert into invoice_item (invoice_id, item_id, quantity, unit_rate, discount) " +
                    "values (?,?,?,?,?)";

    private static final String DELETE_INVOICE_ITEM_BY_INVOICE_ID_SQL =
            "delete from INVOICE_ITEM where invoice_id=?";

    private static final String DELETE_INVOICE_ITEM_BY_ITEM_ID_SQL =
            "delete from INVOICE_ITEM where item_id=?";

    private static final String DELETE_INVOICE_ITEM_SQL =
            "delete from invoice_item where invoice_item_id = ?";

    private static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "select * from invoice_item";


    // Constructor injection
    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Interface implementation
    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL, invoiceItem.getInvoice_id(), invoiceItem.getItem_id(),
                invoiceItem.getQuantity(), invoiceItem.getUnit_rate(), invoiceItem.getDiscount());

        invoiceItem.setInvoice_item_id(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return invoiceItem;
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    public void deleteInvoiceItemByItemId(int item_id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_BY_ITEM_ID_SQL, item_id);
    }

    @Override
    public void deleteInvoiceItemByInvoiceId(int invoice_id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_BY_INVOICE_ID_SQL, invoice_id);
    }

    @Override
    public void deleteInvoiceItem(int invoice_item_id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, invoice_item_id);
    }

    // Helper method to map result set
    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice_item_id(rs.getInt("invoice_item_id"));
        invoiceItem.setInvoice_id(rs.getInt("invoice_id"));
        invoiceItem.setItem_id(rs.getInt("item_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnit_rate(rs.getBigDecimal("unit_rate"));
        invoiceItem.setDiscount(rs.getBigDecimal("discount"));
        return invoiceItem;
    }
}
