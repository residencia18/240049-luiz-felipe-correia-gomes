package br.com.lufecrx.crudexercise.controller;

import java.util.Optional;

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
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        log.info("Searching for all categories");
        Iterable <Category> entities = repository.findAll();
        
        if (entities == null) {
            log.error("No categories found.");
            return ResponseEntity.notFound().build();
        }

        log.info("Categories found successfully.");
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        log.info("Searching for category with id {}", id);
        Optional <Category> opt = repository.findById(id);
        
        if (opt.isEmpty()) {
            log.error("Category with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        Category dto = opt.get();

        log.info("Category with id {} found successfully.", id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody @Valid Category dto) {
        log.info("Saving category with name {}", dto.getCategoryName());

        // Verify if the category already exists in the database
        Optional<Category> existingCategory = repository.findByCategoryName(dto.getCategoryName());
        if (existingCategory.isPresent()) {
            log.error("Category with name {} already exists.", dto.getCategoryName());
            return ResponseEntity.badRequest().build();
        }
        
        log.info("Category with name {} saved successfully.", dto.getCategoryName());
        return ResponseEntity.ok(repository.save(dto));   
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid Category dto, @PathVariable Long id) {
        log.info("Updating category with id {}", id);
        Optional <Category> opt = repository.findById(id);
        
        if (opt.isEmpty()) {
            log.error("Category with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        Category entity = opt.get();
        entity.setCategoryName(dto.getCategoryName());

        log.info("Category with id {} updated successfully.", id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting category with id {}", id);
        Optional <Category> opt = repository.findById(id);
        
        if (opt.isEmpty()) {
            log.error("Category with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        repository.delete(opt.get());

        log.info("Category with id {} deleted successfully.", id);
        return ResponseEntity.ok().build();
    }

}
