package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.invoke.empty.Empty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao{

    private JdbcTemplate jdbcTemplate;

    public static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, order_date, pickup_date, return_date, late_fee) values (?,?,?,?,?)";

    public static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";

    public static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    public static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? where invoice_id = ?";

    public static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    public static final String SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL =
            "select * from invoice where customer_id = ?";

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

        invoice.setInvoiceID(id);
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
    public List<Integer> getAllInvoiceIdsByCustomerId(int customer_id) {
        try {
            return jdbcTemplate.query(SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL, this::mapInvoiceIdToInteger, customer_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public List<Invoice> getAllInvoicesByCustomerId(int customer_id) {
        try {
            return jdbcTemplate.query(SELECT_ALL_INVOICES_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customer_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
        invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
        invoice.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturnDate(rs.getDate("return_date").toLocalDate());
        invoice.setLateFee(rs.getBigDecimal("late_fee"));

        return invoice;
    }

    private Integer mapInvoiceIdToInteger(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("invoice_id");
    }
}
