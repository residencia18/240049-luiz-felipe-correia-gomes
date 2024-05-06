package br.com.lufecrx.crudexercise.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lufecrx.crudexercise.api.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    boolean existsByName(String name);
}
