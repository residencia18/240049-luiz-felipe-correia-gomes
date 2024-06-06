package com.example.atividades.atividade09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testItemConstructor() {
        String expectedName = "Test Item";
        Item item = new Item(expectedName);
        assertEquals(expectedName, item.getName());
    }

    @Test
    public void testGetName() {
        String expectedName = "Test Item";
        Item item = new Item(expectedName);
        assertEquals(expectedName, item.getName());
    }
}