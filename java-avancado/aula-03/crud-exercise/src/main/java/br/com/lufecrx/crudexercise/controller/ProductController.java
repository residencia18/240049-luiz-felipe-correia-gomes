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

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    private final CategoryRepository categoryRepository;
    
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid Product dto) {
        if (!dto.productNameIsValid()) {
            return ResponseEntity.badRequest().build();
        }
        
        // Verify if the category already exists in the database
        Optional<Set<Category>> existingCategories = ProductService.validateCategories(dto, categoryRepository);

        if (existingCategories.isPresent()) {
            dto.setCategories(existingCategories.get());
        } else {
            dto.setCategories(Set.of());
        }

        return ResponseEntity.ok(repository.save(dto));
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable <Product> entityExample = repository.findAll();
        if (entityExample == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entityExample);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional <Product> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product dto = opt.get();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid Product dto) {
        Optional <Product> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!opt.get().productNameIsValid()) {
            return ResponseEntity.badRequest().build();
        }
        
        Product entity = opt.get();
        
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional <Product> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(opt.get());
        return ResponseEntity.ok().build();
    }

}
