package br.com.lufecrx.crudexercise.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.lufecrx.crudexercise.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    UserDetails findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
