package com.example.ClassU1M6Summative.controller;

import com.example.ClassU1M6Summative.dao.InvoiceItemDao;
import com.example.ClassU1M6Summative.dao.ItemDao;
import com.example.ClassU1M6Summative.model.InvoiceItem;
import com.example.ClassU1M6Summative.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemDao itemDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;

    /**
     * CREATE a new Item object and add it to db using POST on route/item
     * @param item type Item
     * @return new item object from db with attached id
     */
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        Item item1 = new Item();
        item1.setName(item.getName());
        item1.setDescription(item.getDescription());
        item1.setDaily_rate(item.getDaily_rate());
        itemDao.addItem(item1);

        return item1;
    }

    /**
     * READ one item by item_id using @pathVariable
     * @param id int
     * @return a single Item object
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItemById(@PathVariable(name = "id") int id) {
        return itemDao.getItem(id);
    }

    /**
     * READ ALL items, no params
     * @return List of Item objects
     */
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    /**
     * UPDATE Item of a given id
     * @param item type Item (new item object)
     * @param id   int (path variable from user input)
     *             no return
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItem(@RequestBody Item item, @PathVariable(name = "id") int id) {
        Item item1 = itemDao.getItem(id);
        if (item.getName() != null) {
            item1.setName(item.getName());
        }
        if (item.getDescription() != null) {
            item1.setDescription(item.getDescription());
        }
        if (item.getDaily_rate() != null) {
            item1.setDaily_rate(item.getDaily_rate());
        }
        itemDao.updateItem(item1);
    }

    /**
     * DELETE one item by Id.
     * First, delete associated invoice_items by item_id
     * @param id int
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItem(@PathVariable(name = "id") int id) {
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        invoiceItemList
                .stream()
                .forEach(ii ->invoiceItemDao.deleteInvoiceItemByItemId(id));
        itemDao.deleteItem(id);
        }
}
