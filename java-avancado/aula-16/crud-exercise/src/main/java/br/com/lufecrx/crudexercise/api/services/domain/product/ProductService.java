package br.com.lufecrx.crudexercise.api.services.domain.product;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.repository.CategoryRepository;
import br.com.lufecrx.crudexercise.api.repository.ProductRepository;
import br.com.lufecrx.crudexercise.exceptions.api.domain.product.InvalidProductNameException;
import br.com.lufecrx.crudexercise.exceptions.api.domain.product.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @CacheEvict(value = "products", allEntries = true)
    public Product createProduct(Product product) {
        log.info("Creating product with name {}", product.getProductName());

        validateProductName(product.getProductName());

        Optional<Set<Category>> existingCategories = validateCategories(product, categoryRepository);

        if (existingCategories.isPresent()) {
            product.setCategories(existingCategories.get());
        }

        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#productId")
    public Optional<Product> getProductById(Long productId) {
        log.info("Getting product by ID {}", productId);

        return Optional.of(productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId)));
    }
    
    // @Cacheable(value = "products")
    // public Iterable<Product> getAllProducts() {
    //     log.info("Getting all products");

    //     if (!productRepository.findAll().iterator().hasNext()) {
    //         throw new ProductsEmptyException();
    //     }

    //     return productRepository.findAll();
    // }

    @CacheEvict(value = "products", allEntries = true)
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

    @CacheEvict(value = "products", allEntries = true)
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
                // If the category doesn't exist, save it to the database and add it to the list
                // of existing categories
                Category savedCategory = categoryRepository.save(category);
                existingCategories.add(savedCategory);
            }
        }

        // Return the list of existing categories
        return Optional.ofNullable(existingCategories.isEmpty() ? null : existingCategories);
    }
}
