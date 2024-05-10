package br.com.lufecrx.crudexercise.api.services.domain.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.model.dto.CategoryDTO;
import br.com.lufecrx.crudexercise.api.repository.CategoryRepository;
import br.com.lufecrx.crudexercise.exceptions.api.domain.category.CategoryAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.api.domain.category.CategoryNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @CacheEvict(value = "categories", allEntries = true)
    public void createCategory(CategoryDTO category) {
        log.info("Creating category with name {}", category.name());

        // Check if the category already exists in the database
        if (categoryRepository.existsByName(category.name())) {
            throw new CategoryAlreadyExistsException(category.name());
        }

        // Create a new category with the given data
        Category newCategory = Category.builder()
                .name(category.name())
                .build();

        // Save the category to the database
        categoryRepository.save(newCategory);
    }

    // @Cacheable(value = "categories")
    // public Iterable<Category> getAllCategories() {
    // log.info("Getting all categories");

    // if (!categoryRepository.findAll().iterator().hasNext()) {
    // throw new CategoriesEmptyException();
    // }

    // return categoryRepository.findAll();
    // }

    @Cacheable(value = "categories", key = "#categoryId")
    public Optional<CategoryDTO> getCategoryById(Long categoryId) {
        log.info("Getting category by ID {}", categoryId);

        Optional<Category> category = categoryRepository.findById(categoryId);
        
        // Return the category if it exists, otherwise throw an exception
        if (category.isPresent()) {
            return Optional.of(CategoryDTO.from(category.get()));
        } else {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void updateCategory(Long categoryId, CategoryDTO updatedCategory) {
        log.info("Updating category with ID {}", categoryId);

        // Check if the category exists in the database
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        // Update the category with the new data
        category.setName(updatedCategory.name());

        // Save the updated category to the database
        categoryRepository.save(category);
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void deleteCategory(Long categoryId) {
        log.info("Deleting category with ID {}", categoryId);

        // Check if the category exists in the database
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }

        // Delete the category from the database 
        categoryRepository.deleteById(categoryId);
    }

}
