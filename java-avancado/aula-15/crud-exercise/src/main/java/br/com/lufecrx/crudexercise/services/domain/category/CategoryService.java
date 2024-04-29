package br.com.lufecrx.crudexercise.services.domain.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.category.CategoryAlreadyExistsException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.category.CategoryNotFoundException;
import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @CacheEvict(value = "categories", allEntries = true)
    public Category createCategory(Category category) {
        log.info("Creating category with name {}", category.getName());

        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        return categoryRepository.save(category);
    }

    // @Cacheable(value = "categories")
    // public Iterable<Category> getAllCategories() {
    //     log.info("Getting all categories");

    //     if (!categoryRepository.findAll().iterator().hasNext()) {
    //         throw new CategoriesEmptyException();
    //     }

    //     return categoryRepository.findAll();
    // }

    @Cacheable(value = "categories", key = "#categoryId")
    public Optional<Category> getCategoryById(Long categoryId) {
        log.info("Getting category by ID {}", categoryId);

        return Optional.of(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId)));
    }

    @CacheEvict(value = "categories", allEntries = true)
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

    @CacheEvict(value = "categories", allEntries = true)
    public void deleteCategory(Long categoryId) {
        log.info("Deleting category with ID {}", categoryId);

        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

}
