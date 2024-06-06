package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserTest {
    
    @Test
    public void testConstructor() {
        User user = new User("Test User", "testuser@example.com");
        assertNotNull(user);
    }

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
