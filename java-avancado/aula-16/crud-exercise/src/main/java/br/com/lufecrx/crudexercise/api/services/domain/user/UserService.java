package br.com.lufecrx.crudexercise.api.services.domain.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.user.UserNotFoundException;
import br.com.lufecrx.crudexercise.api.model.User;
import br.com.lufecrx.crudexercise.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @CacheEvict(value = "users", allEntries = true)
    public User createUser(User user) {
        log.info("Creating user with name {}", user.getName());

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }

        return userRepository.save(user);
    }

    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(Long id) {
        log.info("Getting user with id {}", id);
        
        return Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Cacheable(value = "users", key = "#email")
    public Optional<User> getUserByEmail(String email) {
        log.info("Getting user with email {}", email);
        
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException(email);
        }

        return userRepository.findByEmail(email);
    }

    // @Cacheable(value = "users")
    // public Iterable<User> getAllUsers() {
    //     log.info("Getting all users");

    //     if (!userRepository.findAll().iterator().hasNext()) {
    //         throw new UsersEmptyException();
    //     }

    //     return userRepository.findAll();
    // }

    @CacheEvict(value = "users", allEntries = true)
    public User updateUser(Long id, User updatedUser) {
        log.info("Updating user with id {}", id);
        Optional<User> existingUser = userRepository.findById(id);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setBirthDate(updatedUser.getBirthDate());
            user.setMobilePhone(updatedUser.getMobilePhone());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteUser(Long id) {
        log.info("Deleting user with id {}", id);
        Optional<User> existingUser = userRepository.findById(id);
        
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

}
