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
import br.com.lufecrx.crudexercise.api.model.dto.CategoryDTO;
import br.com.lufecrx.crudexercise.api.model.dto.ProductDTO;
import br.com.lufecrx.crudexercise.api.repository.CategoryRepository;
import br.com.lufecrx.crudexercise.api.repository.ProductRepository;
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
    public void createProduct(ProductDTO product) {
        log.info("Creating product with name {}", product.name());

        // Return the list of categories that already exist in the database and create the ones that don't
        Optional<Set<Category>> categories = validateCategories(product, categoryRepository);

        // Create a new product with the given data
        Product newProduct = Product.builder()
                .productName(product.name())
                .price(product.price())
                .categories(categories.orElse(null))
                .build();

        // Save the product to the database
        productRepository.save(newProduct);
    }

    @Cacheable(value = "products", key = "#productId")
    public Optional<ProductDTO> getProductById(Long productId) {
        log.info("Getting product by ID {}", productId);

        Optional<Product> product = productRepository.findById(productId);
        
        // Return the product if it exists, otherwise throw an exception
        if (product.isPresent()) {
            return Optional.of(ProductDTO.from(product.get()));
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    // @Cacheable(value = "products")
    // public Iterable<Product> getAllProducts() {
    // log.info("Getting all products");

    // if (!productRepository.findAll().iterator().hasNext()) {
    // throw new ProductsEmptyException();
    // }

    // return productRepository.findAll();
    // }

    @CacheEvict(value = "products", allEntries = true)
    public void updateProduct(Long productId, ProductDTO updatedProduct) {
        log.info("Updating product with ID {}", productId);

        // Return the list of categories that already exist in the database and create the ones that don't
        Optional<Set<Category>> categories = validateCategories(updatedProduct, categoryRepository);

        // Get the existing product from the database
        Optional<Product> existingProduct = productRepository.findById(productId);

        // If the product exists, update it. Otherwise, throw an exception
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();

            product.setProductName(updatedProduct.name());
            product.setPrice(updatedProduct.price());
            product.setCategories(categories.orElse(null));

            productRepository.save(product);
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID {}", productId);

        Optional<Product> existingProduct = productRepository.findById(productId);

        // If the product exists, delete it. Otherwise, throw an exception
        if (existingProduct.isPresent()) {
            productRepository.delete(existingProduct.get());
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    // Verify if the category already exists in the database
    public Optional<Set<Category>> validateCategories(ProductDTO product, CategoryRepository categoryRepository) {
        log.info("Validating categories for product with name {}", product.name());

        Set<Category> existingCategories = new HashSet<>();

        for (CategoryDTO categoryDTO : product.categories()) {
            Optional<Category> existingCategory = categoryRepository.findByName(categoryDTO.name());

            if (existingCategory.isPresent()) {
                // If the category already exists, add it to the list of existing categories
                existingCategories.add(existingCategory.get());
            } else {
                // If the category doesn't exist, save it to the database and add it to the list of existing categories
                Category category = Category.builder()
                                .name(categoryDTO.name())
                                .build();

                Category savedCategory = categoryRepository.save(category);
                existingCategories.add(savedCategory);
            }
        }
        // Return the list of existing categories
        return Optional.ofNullable(existingCategories.isEmpty() ? null : existingCategories);
    }
}
