package br.com.lufecrx.crudexercise.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.crudexercise.model.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Long> {
    
}
