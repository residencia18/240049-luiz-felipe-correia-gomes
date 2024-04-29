package br.com.lufecrx.crudexercise.services.caching;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.model.User;
import br.com.lufecrx.crudexercise.repository.UserRepository;
import br.com.lufecrx.crudexercise.services.domain.user.UserService;
import br.com.lufecrx.crudexercise.services.domain.user.UserServicePaginable;

@SpringBootTest
public class UserCachingIntegrationTest {
    
    @Autowired
    private UserServicePaginable userServicePag;

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @SpyBean
    private UserRepository userRepository;

    private Faker faker;

    private List<User> users;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        users = fillUsers();
        userRepository.saveAll(users);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        cacheManager.getCache("users").clear();
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
    public void testCaching() {
        // Call the method 100 times
        for (int i = 0; i < 100; i++) {
            userServicePag.getWithPagination(1, 5, new String[] { "name", "asc" });
        }

        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name").ascending());

        // Verify that the method was called only once due to caching
        verify(userRepository, times(1)).findAll(pageRequest);
    }

    @Test
    public void testCacheEvict() {
        // Call the method 100 times
        for (int i = 0; i < 100; i++) {
            userServicePag.getWithPagination(1, 5, new String[] { "name", "asc" });
        }

        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name").ascending());

        // Verify that the method was called only once due to caching
        verify(userRepository, times(1)).findAll(pageRequest);
        
        // Delete a wishlist to evict the cache
        userService.deleteUser(users.get(0).getId());     
        
        // Call the method again
        userServicePag.getWithPagination(1, 5, new String[] { "name", "asc" });

        // Verify that the method was called again due to cache eviction
        verify(userRepository, times(2)).findAll(pageRequest);
    }
}
