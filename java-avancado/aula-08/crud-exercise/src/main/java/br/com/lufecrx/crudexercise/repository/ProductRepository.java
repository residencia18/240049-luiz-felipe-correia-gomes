package br.com.lufecrx.crudexercise.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.crudexercise.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}   
