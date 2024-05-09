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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@ApiResponse(responseCode = "403", description = "You are not authorized to access this resource")
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category found"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> opt = categoryService.getCategoryById(id);
        return ResponseEntity.ok(opt.get());
    }

    @Operation(summary = "Create a new category", description = "Create a new category with the given data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category created"),
        @ApiResponse(responseCode = "409", description = "Category already exists")
    })
    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Category dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.ok(bundle.getString("category.successfully_created"));   
    }

    @Operation(summary = "Update a category", description = "Update a category with the given data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category updated"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid Category dto, @PathVariable Long id) {
        categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(bundle.getString("category.successfully_updated"));
    }

    @Operation(summary = "Delete a category", description = "Delete a category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category deleted"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(bundle.getString("category.successfully_deleted"));
    }

}
