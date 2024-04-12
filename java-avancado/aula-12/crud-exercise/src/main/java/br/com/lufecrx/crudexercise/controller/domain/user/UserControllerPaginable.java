package br.com.lufecrx.crudexercise.controller.domain.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.User;
import br.com.lufecrx.crudexercise.services.domain.user.UserServicePaginable;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paginable/users")
public class UserControllerPaginable extends UserController {
    
    @Qualifier("paginable")
    private UserServicePaginable userService;
    
    @GetMapping("/page/{page}/size/5")
    public ResponseEntity<Iterable<User>> findAllWithPaginationAndSizeFive(@PathVariable int page) {
        Iterable<User> entities = userService.getWithPagination(page, 5); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/10")
    public ResponseEntity<Iterable<User>> findAllWithPaginationAndSizeTen(@PathVariable int page) {
        Iterable<User> entities = userService.getWithPagination(page, 10); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/20")
    public ResponseEntity<Iterable<User>> findAllWithPaginationAndSizeTwenty(@PathVariable int page) {
        Iterable<User> entities = userService.getWithPagination(page, 20); 
        return ResponseEntity.ok(entities);
    }
    
}
