package br.com.lufecrx.crudexercise.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.exceptions.product.InvalidProductNameException;
import br.com.lufecrx.crudexercise.exceptions.product.ProductNotFoundException;
import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import br.com.lufecrx.crudexercise.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public Product createProduct(Product product) {
        log.info("Creating product with name {}", product.getProductName());
        
        validateProductName(product.getProductName());
        Optional<Set<Category>> existingCategories = validateCategories(product, categoryRepository);
        
        if (existingCategories.isPresent()) {
            product.setCategories(existingCategories.get());
        }
        
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long productId) {
        log.info("Getting product by ID {}", productId);
        
        return productRepository.findById(productId);
    }

    public Iterable<Product> getAllProducts() {
        log.info("Getting all products");
        
        return productRepository.findAll();
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        log.info("Updating product with ID {}", productId);
        
        Optional<Product> existingProduct = productRepository.findById(productId);
        
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategories(updatedProduct.getCategories());
            
            validateProductName(product.getProductName());
            Optional<Set<Category>> existingCategories = validateCategories(product, categoryRepository);
            
            if (existingCategories.isPresent()) {
                product.setCategories(existingCategories.get());
            }
            
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID {}", productId);
        
        Optional<Product> existingProduct = productRepository.findById(productId);
        
        if (existingProduct.isPresent()) {
            productRepository.delete(existingProduct.get());
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    public static void validateProductName(String productName) {
        log.info("Validating product name {}", productName);
        
        if (productName == null || productName.isBlank()) {
            throw new InvalidProductNameException(productName);
        }
    }
    
    // Verify if the category already exists in the database
    public static Optional<Set<Category>> validateCategories(Product product, CategoryRepository categoryRepository) {
        log.info("Validating categories for product with name {}", product.getProductName());
        
        Set<Category> existingCategories = new HashSet<>();

        for (Category category : product.getCategories()) {
            Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
        
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
