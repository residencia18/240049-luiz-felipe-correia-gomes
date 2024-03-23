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
import br.com.lufecrx.crudexercise.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        Iterable<Category> entities = categoryService.getAllCategories();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> opt = categoryService.getCategoryById(id);
        return ResponseEntity.ok(opt.get());
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Category dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.ok("Category created successfully.");   
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid Category dto, @PathVariable Long id) {
        categoryService.updateCategory(id, dto);
        return ResponseEntity.ok("Category updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }

}
