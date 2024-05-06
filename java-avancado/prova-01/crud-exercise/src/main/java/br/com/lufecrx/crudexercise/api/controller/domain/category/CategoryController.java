package br.com.lufecrx.crudexercise.api.controller.domain.category;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.services.domain.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("standard") CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    // @GetMapping 
    // public ResponseEntity<Iterable<Category>> findAll() {
    //     Iterable<Category> entities = categoryService.getAllCategories();
    //     return ResponseEntity.ok(entities);
    // }

    @Operation(summary = "Find a category by its ID", description = "Find a category by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> opt = categoryService.getCategoryById(id);
        return ResponseEntity.ok(opt.get());
    }

    @Operation(summary = "Create a new category", description = "Create a new category with the given data")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Category dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.ok(bundle.getString("category.successfully_created"));   
    }

    @Operation(summary = "Update a category", description = "Update a category with the given data")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid Category dto, @PathVariable Long id) {
        categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(bundle.getString("category.successfully_updated"));
    }

    @Operation(summary = "Delete a category", description = "Delete a category by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(bundle.getString("category.successfully_deleted"));
    }

}
