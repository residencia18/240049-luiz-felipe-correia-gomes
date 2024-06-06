package com.example.atividades.atividade09;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemCollectionTest {
    private ItemCollection itemCollection;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        itemCollection = new ItemCollection();
        item1 = new Item("Item 1");
        item2 = new Item("Item 2");
    }

    @Test
    public void testAddItem() {
        itemCollection.addItem(item1);
        assertTrue(itemCollection.getItems().contains(item1));
    }

    @Test
    public void testAddNullItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            itemCollection.addItem(null);
        });
    }

    @Test
    public void testRemoveItem() {
        itemCollection.addItem(item1);
        itemCollection.removeItem(item1);
        assertFalse(itemCollection.getItems().contains(item1));
    }

    @Test
    public void testRemoveNonExistentItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            itemCollection.removeItem(item2);
        });
    }

    @Test
    public void testGetItems() {
        itemCollection.addItem(item1);
        itemCollection.addItem(item2);
        List<Item> items = itemCollection.getItems();
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }
}