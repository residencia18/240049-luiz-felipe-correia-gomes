package br.com.lufecrx.crudexercise.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.crudexercise.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);
    
}
