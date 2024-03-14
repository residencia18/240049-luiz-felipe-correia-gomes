package br.com.lufecrx.crudexercise.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.crudexercise.model.ProductModel;


public interface ProductRepository extends CrudRepository<ProductModel, Long> {
    
}   
