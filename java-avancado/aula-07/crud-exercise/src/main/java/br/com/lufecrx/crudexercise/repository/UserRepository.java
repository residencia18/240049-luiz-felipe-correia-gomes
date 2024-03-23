package br.com.lufecrx.crudexercise.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.crudexercise.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    
}
