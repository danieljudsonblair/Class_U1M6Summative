package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao{

    private JdbcTemplate jdbcTemplate;

    public static final String INSERT_INVOICE_SQL =
            "insert into invoice(invoice_id, customer_id, order_date, pickup_date, return_date, late_fee) values (?,?,?,?,?,?)";

    public static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";

    public static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    public static final String UPDATE_INVOICE_SQL =
            "update customer set customer_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? where invoice_id = ?";

    public static final String DELETE_INVOICE_SQL =
            "delete from customer where customer_id = ?";

    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getCustomerID(), invoice.getOrderDate(),
                invoice.getPickupDate(), invoice.getReturnDate(), invoice.getLateFee());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoice.setCustomerID(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int invoiceID) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoiceID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getCustomerID(), invoice.getOrderDate(),
                invoice.getPickupDate(), invoice.getReturnDate(), invoice.getLateFee(), invoice.getInvoiceID());

    }

    @Override
    public void deleteInvoice(int invoiceID) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceID);
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(rs.getInt("invoice_id"));
        invoice.setCustomerID(rs.getInt("customer_id"));
        invoice.setOrderDate(rs.getString("order_date"));
        invoice.setPickupDate(rs.getString("pickup_date"));
        invoice.setReturnDate(rs.getString("return_date"));
        invoice.setLateFee(rs.getBigDecimal("late_fee"));

        return invoice;
    }
}
