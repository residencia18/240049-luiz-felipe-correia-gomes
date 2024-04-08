package br.com.lufecrx.crudexercise.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.model.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    private Faker faker;

    private User generateFakerUser() {
        User user = new User();
        user.setName(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setMobilePhone(faker.number().digits(11));
        user.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return user;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        user = generateFakerUser();
        entityManager.persistAndFlush(user);
    }

    @Test
    public void testSaveUser() {
        User newUser = generateFakerUser();
        User savedUser = userRepository.save(newUser);
        assertThat(savedUser).isNotNull();
    }

    @Test
    public void testFindAllUsers() {
        Iterable<User> users = userRepository.findAll();
        assertThat(users).isNotEmpty();
    }

    @Test
    public void testFindUserById() {
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent().hasValue(user);
    }

    @Test
    public void testUpdateUser() {
        String updatedUsername = faker.name().username();
        user.setName(updatedUsername);
        userRepository.save(user);
        Optional<User> updatedUser = userRepository.findById(user.getId());
        assertThat(updatedUser).isPresent().hasValueSatisfying(u -> assertThat(user.getName()).isEqualTo(updatedUsername));
    }

    @Test
    public void testDeleteUser() {
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertThat(deletedUser).isNotPresent();
    }
}