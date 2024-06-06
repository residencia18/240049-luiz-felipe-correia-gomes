package com.example.atividades.atividade07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGetName() {
        User user = new User("Test User", "testuser@example.com");
        String expectedName = "Test User";
        assertEquals(expectedName, user.getName());
    }

    @Test
    public void testGetEmail() {
        User user = new User("Test User", "testuser@example.com");
        String expectedEmail = "testuser@example.com";
        assertEquals(expectedEmail, user.getEmail());
    }
}