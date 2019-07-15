package com.example.ClassU1M6Summative.dao;

import com.example.ClassU1M6Summative.model.Item;

import java.util.List;

public interface ItemDao {

    Item addItem(Item item);

    Item getItem(int item_id);

    List<Item> getAllItems();

    void updateItem(Item item);

    /**
     * Delete item by Item_id. To successfully use this method,
     * first address foreign key constraints. Invoice_item uses item as a FK, so delete
     * invoice_item by item_id first. Then you may delete item.
     * @param item_id int
     */
    void deleteItem(int item_id);
}
