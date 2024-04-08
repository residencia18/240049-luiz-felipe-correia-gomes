// FILEPATH: /C:/Users/85938454522/Desktop/Workspace/240049-luiz-felipe-correia-gomes/java-avancado/aula-08/crud-exercise/src/test/java/br/com/lufecrx/crudexercise/controller/UserControllerTest.java

package br.com.lufecrx.crudexercise.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.exceptions.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.user.UserNotFoundException;
import br.com.lufecrx.crudexercise.exceptions.user.UsersEmptyException;
import br.com.lufecrx.crudexercise.model.User;
import br.com.lufecrx.crudexercise.services.UserService;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private Faker faker;
    
    private ResourceBundle bundle;

    private List<User> users;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        users = fillUsers();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    public List<User> fillUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setName(faker.name().fullName());

            users.add(user);
        }

        return users;
    }

    @Test
    public void testSave() {
        for (User user : users) {
            when(userService.createUser(any(User.class))).thenReturn(user);

            ResponseEntity<String> response = userController.save(user);

            assertEquals(bundle.getString("user.successfully_created"), response.getBody());
            verify(userService, times(1)).createUser(user);
        }
    }

    @Test
    public void testFindAll() {
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<Iterable<User>> response = userController.findAll();

        assertEquals(10, ((Collection<?>) response.getBody()).size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testFindById() {
        User user = users.get(4);
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        ResponseEntity<Optional<User>> response = userController.findById(1L);

        assertEquals(user, response.getBody().get());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testFindByEmail() {
        User user = users.get(8);
        when(userService.getUserByEmail(any(String.class))).thenReturn(Optional.of(user));

        String fakerEmail = faker.internet().emailAddress();

        ResponseEntity<Optional<User>> response = userController.findByEmail(fakerEmail);

        assertEquals(user, response.getBody().get());
        verify(userService, times(1)).getUserByEmail(fakerEmail);
    }

    @Test
    public void testDelete() {
        doNothing().when(userService).deleteUser(anyLong());

        ResponseEntity<String> response = userController.delete(1L);

        assertEquals(bundle.getString("user.successfully_deleted"), response.getBody());
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    public void testUpdate() {
        User user = users.get(1);
        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);

        ResponseEntity<String> response = userController.update(1L, user);

        assertEquals(bundle.getString("user.successfully_updated"), response.getBody());
        verify(userService, times(1)).updateUser(1L, user);
    }

    @Test
    public void testWhenEmailAlreadyExists() {
        User user = users.get(1);
        when(userController.save(any(User.class))).thenThrow(new EmailAlreadyExistsException(user.getEmail()));

        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            userController.save(user);
        });

        String expectedMessage = bundle.getString("user.already_exists").replace("{email}", user.getEmail());
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenUserNotFoundById() {
        when(userController.findById(anyLong())).thenThrow(new UserNotFoundException(1L));

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userController.findById(1L);
        });

        String expectedMessage = bundle.getString("user.not_found").replace("{id}", "1");
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenUserNotFoundByEmail() {
        String fakerEmail = faker.internet().emailAddress();
        when(userController.findByEmail(any(String.class))).thenThrow(new UserNotFoundException(fakerEmail));

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userController.findByEmail("email");
        });

        String expectedMessage = bundle.getString("user.not_found").replace("{email}", "email");
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenUsersEmpty() {
        when(userController.findAll()).thenThrow(new UsersEmptyException());

        Exception exception = assertThrows(UsersEmptyException.class, () -> {
            userController.findAll();
        });

        String expectedMessage = bundle.getString("user.empty_list");
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    

}