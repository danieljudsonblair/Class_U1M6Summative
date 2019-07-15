package com.example.ClassU1M6Summative.model;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    //--------------------------------------------------------------------
    // Annotate each property to be not null and label them
    // private to adhere to data hiding practices - Public
    // interface, private implementation
    //--------------------------------------------------------------------
    @NotEmpty
    private int item_id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private BigDecimal daily_rate;

    //---------------------------------------------------------------------
    // Getters and Setters
    //---------------------------------------------------------------------
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(BigDecimal daily_rate) {
        this.daily_rate = daily_rate;
    }

    //---------------------------------------------------------------------
    // equals() and hasCode()
    //---------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getItem_id() == item.getItem_id() &&
                getName().equals(item.getName()) &&
                getDescription().equals(item.getDescription()) &&
                getDaily_rate().equals(item.getDaily_rate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem_id(), getName(), getDescription(), getDaily_rate());
    }
}
