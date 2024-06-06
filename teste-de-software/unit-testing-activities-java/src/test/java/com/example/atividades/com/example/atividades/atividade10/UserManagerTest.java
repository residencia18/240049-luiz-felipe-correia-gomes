package com.example.atividades.atividade10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserManagerTest {
    private UserService mockUserService;
    private UserManager userManager;

    @BeforeEach
    public void setUp() {
        mockUserService = mock(UserService.class);
        userManager = new UserManager(mockUserService);
    }

    @Test
    public void testFetchUserInfo_UserExists() {
        User expectedUser = new User("Test User", "testuser@example.com");
        when(mockUserService.getUserInfo(1)).thenReturn(expectedUser);

        User actualUser = userManager.fetchUserInfo(1);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFetchUserInfo_UserDoesNotExist() {
        when(mockUserService.getUserInfo(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userManager.fetchUserInfo(1));
    }
}