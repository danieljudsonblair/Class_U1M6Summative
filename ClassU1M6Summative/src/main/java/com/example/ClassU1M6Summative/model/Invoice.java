package com.example.ClassU1M6Summative.model;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    @NotEmpty
    private int invoiceID;
    @NotEmpty
    private int customerID;
    @NotEmpty
    private LocalDate orderDate;
    @NotEmpty
    private LocalDate pickupDate;
    @NotEmpty
    private LocalDate returnDate;
    @NotEmpty
    private BigDecimal lateFee;

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }


    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceID() == invoice.getInvoiceID() &&
                getCustomerID() == invoice.getCustomerID() &&
                getOrderDate().equals(invoice.getOrderDate()) &&
                getPickupDate().equals(invoice.getPickupDate()) &&
                getReturnDate().equals(invoice.getReturnDate()) &&
                getLateFee().equals(invoice.getLateFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceID(), getCustomerID(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee());
    }
}
