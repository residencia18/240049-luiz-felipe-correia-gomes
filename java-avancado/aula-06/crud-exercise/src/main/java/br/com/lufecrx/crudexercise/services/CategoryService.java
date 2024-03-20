package br.com.lufecrx.crudexercise.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.exceptions.category.CategoriesEmptyException;
import br.com.lufecrx.crudexercise.exceptions.category.CategoryAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.category.CategoryNotFoundException;
import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        log.info("Creating category with name {}", category.getName());

        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        return categoryRepository.save(category);
    }

    public Iterable<Category> getAllCategories() {  
        log.info("Getting all categories");

        if (!categoryRepository.findAll().iterator().hasNext()) {
            throw new CategoriesEmptyException();
        }

        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        log.info("Getting category by ID {}", categoryId);

        return Optional.of(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId)));
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) {
        log.info("Updating category with ID {}", categoryId);

        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(updatedCategory.getName());
            return categoryRepository.save(category);
        }
        throw new CategoryNotFoundException(categoryId);
    }

    public void deleteCategory(Long categoryId) {
        log.info("Deleting category with ID {}", categoryId);

        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }
       
}
