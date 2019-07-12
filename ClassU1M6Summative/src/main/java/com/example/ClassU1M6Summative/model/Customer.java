package com.example.ClassU1M6Summative.model;

import java.util.Objects;

public class Customer {
    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String company;
    private String phone;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCustomer_id() == customer.getCustomer_id() &&
                Objects.equals(getFirst_name(), customer.getFirst_name()) &&
                Objects.equals(getLast_name(), customer.getLast_name()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getCompany(), customer.getCompany()) &&
                Objects.equals(getPhone(), customer.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer_id(), getFirst_name(), getLast_name(), getEmail(), getCompany(), getPhone());
    }
}
