package com.example.ClassU1M6Summative.model;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {

    //--------------------------------------------------------------------
    // Annotate each property to be not null and label them
    // private to adhere to data hiding practices - Public
    // interface, private implementation
    //--------------------------------------------------------------------
    @NotEmpty
    private int invoice_item_id;
    @NotEmpty
    private int invoice_id;
    @NotEmpty
    private int item_id;
    @NotEmpty
    private int quantity;
    @NotEmpty
    private BigDecimal unit_rate;
    @NotEmpty
    private BigDecimal discount;

    //---------------------------------------------------------------------
    // Getters and Setters
    //---------------------------------------------------------------------

    public int getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_rate() {
        return unit_rate;
    }

    public void setUnit_rate(BigDecimal unit_rate) {
        this.unit_rate = unit_rate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    //---------------------------------------------------------------------
    // equals() and hasCode()
    //---------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return getInvoice_item_id() == that.getInvoice_item_id() &&
                getInvoice_id() == that.getInvoice_id() &&
                getItem_id() == that.getItem_id() &&
                getQuantity() == that.getQuantity() &&
                getUnit_rate().equals(that.getUnit_rate()) &&
                getDiscount().equals(that.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice_item_id(), getInvoice_id(), getItem_id(), getQuantity(), getUnit_rate(), getDiscount());
    }
}
