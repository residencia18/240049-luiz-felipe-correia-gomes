package br.com.lufecrx.crudexercise.services.domain.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.user.UserNotFoundException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.user.UsersEmptyException;
import br.com.lufecrx.crudexercise.model.User;
import br.com.lufecrx.crudexercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public User createUser(User user) {
        log.info("Creating user with name {}", user.getName());

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        log.info("Getting user with id {}", id);
        
        return Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public Optional<User> getUserByEmail(String email) {
        log.info("Getting user with email {}", email);
        
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException(email);
        }

        return userRepository.findByEmail(email);
    }

    public Iterable<User> getAllUsers() {
        log.info("Getting all users");

        if (!userRepository.findAll().iterator().hasNext()) {
            throw new UsersEmptyException();
        }

        return userRepository.findAll();
    }

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
