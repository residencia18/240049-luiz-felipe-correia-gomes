package br.com.lufecrx.crudexercise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

public class UserTest {

    private List<User> users;

    private Faker faker;

    @BeforeEach
    public void init() {
        faker = new Faker();
        users = fillUsers();
    }

    public List<User> fillUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setName(faker.name().fullName());
            user.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            user.setMobilePhone(faker.phoneNumber().cellPhone());

            users.add(user);
        }

        return users;
    }

    @Test
    public void testName() {
        for (User user : users) {
            String nameValue = faker.name().fullName();
            user.setName(nameValue);
            assertEquals(nameValue, user.getName());
        }
    }

    @Test
    public void testEmail() {
        for (User user : users) {
            String emailValue = faker.internet().emailAddress();
            user.setEmail(emailValue);
            assertEquals(emailValue, user.getEmail());
        }
    }

    @Test
    public void testBirthDate() {
        for (User user : users) {
            LocalDate birthDateValue = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            user.setBirthDate(birthDateValue);
            assertEquals(birthDateValue, user.getBirthDate());
        }
    }

    @Test
    public void testMobilePhone() {
        for (User user : users) {
            String mobilePhoneValue = faker.phoneNumber().cellPhone();
            user.setMobilePhone(mobilePhoneValue);
            assertEquals(mobilePhoneValue, user.getMobilePhone());
        }
    }
}