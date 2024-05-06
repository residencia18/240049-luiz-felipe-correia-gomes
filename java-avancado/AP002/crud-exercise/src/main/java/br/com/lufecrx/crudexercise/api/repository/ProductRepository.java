package br.com.lufecrx.crudexercise.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lufecrx.crudexercise.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}   
