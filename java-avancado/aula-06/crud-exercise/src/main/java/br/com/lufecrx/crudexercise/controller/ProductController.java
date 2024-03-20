package br.com.lufecrx.crudexercise.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import br.com.lufecrx.crudexercise.repository.ProductRepository;
import br.com.lufecrx.crudexercise.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    private final CategoryRepository categoryRepository;
    
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid Product dto) {
        log.info("Saving product with name {}", dto.getProductName());

        if (!dto.productNameIsValid()) {
            log.error("Invalid product name: {}", dto.getProductName());
            return ResponseEntity.badRequest().build();
        }
        
        // Verify if the category already exists in the database
        Optional<Set<Category>> existingCategories = ProductService.validateCategories(dto, categoryRepository);

        if (existingCategories.isPresent()) {
            log.info("Categories validated successfully for product with name {}", dto.getProductName());
            dto.setCategories(existingCategories.get());
        } else {
            log.error("No categories found for product with name {}", dto.getProductName());
            dto.setCategories(Set.of());
        }

        log.info("Product with name {} saved successfully.", dto.getProductName());
        return ResponseEntity.ok(repository.save(dto));
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        log.info("Searching for all products");

        Iterable <Product> entityExample = repository.findAll();
        
        if (entityExample == null) {
            log.error("No products found.");
            return ResponseEntity.notFound().build();
        }
        
        log.info("Products found successfully.");
        return ResponseEntity.ok(entityExample);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        log.info("Searching for product with id {}", id);

        Optional <Product> opt = repository.findById(id);

        if (opt.isEmpty()) {
            log.error("Product with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        
        Product dto = opt.get();

        log.info("Product with id {} found successfully.", id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid Product dto) {
        log.info("Updating product with id {}", id);

        Optional <Product> opt = repository.findById(id);

        if (opt.isEmpty()) {
            log.error("Product with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        if (!opt.get().productNameIsValid()) {
            log.error("Invalid product name: {}", dto.getProductName());
            return ResponseEntity.badRequest().build();
        }
        
        Product entity = opt.get();
        log.info("Old product name: {}", entity.getProductName());
        log.info("New product name: {}", dto.getProductName());

        log.info("Old price: {}", entity.getPrice());
        log.info("New price: {}", dto.getPrice());

        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());
        

        log.info("Product with id {} updated successfully.", id);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting product with id {}", id);
        Optional <Product> opt = repository.findById(id);

        if (opt.isEmpty()) {
            log.error("Product with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        
        repository.delete(opt.get());
        
        log.info("Product with id {} deleted successfully.", id);
        return ResponseEntity.ok().build();
    }
}
