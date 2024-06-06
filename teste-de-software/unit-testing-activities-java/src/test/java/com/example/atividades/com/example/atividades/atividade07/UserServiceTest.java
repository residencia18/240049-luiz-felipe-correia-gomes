package com.example.atividades.atividade07;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private Database db;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        db = mock(Database.class);
        userService = new UserService(db);
    }

    @Test
    public void testSaveUserWithValidUser() {
        User user = new User("John Doe", "john.doe@example.com");
        userService.saveUser(user);
        verify(db, times(1)).saveUser(user);
    }

    @Test
    public void testSaveUserWithNullName() {
        User user = new User(null, "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testSaveUserWithEmptyName() {
        User user = new User("", "john.doe@example.com");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testSaveUserWithNullEmail() {
        User user = new User("John Doe", null);
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }

    @Test
    public void testSaveUserWithEmptyEmail() {
        User user = new User("John Doe", "");
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }
}