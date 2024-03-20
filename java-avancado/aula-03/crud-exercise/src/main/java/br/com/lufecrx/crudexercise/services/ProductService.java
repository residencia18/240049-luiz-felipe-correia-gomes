package br.com.lufecrx.crudexercise.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    
    public static Optional<Set<Category>> validateCategories(Product product, CategoryRepository categoryRepository) {
        // Verify if the category already exists in the database
        Set<Category> existingCategories = new HashSet<>();
        for (Category category : product.getCategories()) {
            Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
            if (existingCategory.isPresent()) {
                // If the category already exists, add it to the list of existing categories
                existingCategories.add(existingCategory.get());
            } else {
                // If the category doesn't exist, save it to the database and add it to the list of existing categories
                Category savedCategory = categoryRepository.save(category);
                existingCategories.add(savedCategory);
            }
        }

        // Return the list of existing categories
        return Optional.ofNullable(existingCategories.isEmpty() ? null : existingCategories);
    }
}
