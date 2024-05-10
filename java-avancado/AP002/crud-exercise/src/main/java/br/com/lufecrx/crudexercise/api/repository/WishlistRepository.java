package br.com.lufecrx.crudexercise.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lufecrx.crudexercise.api.model.Wishlist;
import br.com.lufecrx.crudexercise.auth.model.User;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    boolean existsByName(String name);

    boolean existsByNameAndUser(String name, User user);

    Optional<Wishlist> findByName(String name);

    Optional<Wishlist> findByNameAndUser(String name, User user);
}
