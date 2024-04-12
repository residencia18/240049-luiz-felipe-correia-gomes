package br.com.lufecrx.crudexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lufecrx.crudexercise.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}   
