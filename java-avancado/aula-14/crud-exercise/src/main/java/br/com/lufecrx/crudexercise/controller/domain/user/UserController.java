package br.com.lufecrx.crudexercise.controller.domain.user;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.User;
import br.com.lufecrx.crudexercise.services.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Qualifier("standard")
    private UserService userService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    
    // @GetMapping
    // public ResponseEntity<Iterable<User>> findAll() {
    //     Iterable<User> users = userService.getAllUsers();
    //     return ResponseEntity.ok(users);
    // }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid User dto) {
        userService.createUser(dto);
        return ResponseEntity.ok(bundle.getString("user.successfully_created"));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> findByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(bundle.getString("user.successfully_deleted"));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid User dto) {
        userService.updateUser(id, dto);
        return ResponseEntity.ok(bundle.getString("user.successfully_updated"));
    }    
}
