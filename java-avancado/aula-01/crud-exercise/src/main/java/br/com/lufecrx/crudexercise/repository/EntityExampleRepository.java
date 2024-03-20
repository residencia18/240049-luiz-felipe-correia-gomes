package br.com.lufecrx.crudexercise.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.crudexercise.model.EntityExampleModel;

public interface EntityExampleRepository extends CrudRepository<EntityExampleModel, Long> {
    
}   
