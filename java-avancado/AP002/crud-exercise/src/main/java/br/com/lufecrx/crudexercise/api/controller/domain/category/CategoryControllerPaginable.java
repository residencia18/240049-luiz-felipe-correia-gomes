package br.com.lufecrx.crudexercise.api.controller.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.services.domain.category.CategoryServicePaginable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/paginable/categories")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Categories found"),
    @ApiResponse(responseCode = "404", description = "Categories not found")
})
public class CategoryControllerPaginable {

    private CategoryServicePaginable categoryService;

    @Autowired
    public CategoryControllerPaginable(@Qualifier("paginable") CategoryServicePaginable categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Find all categories with pagination", description = "Find all categories with pagination")
    @GetMapping("/page/{page}")
    public ResponseEntity<Iterable<Category>> findAll(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        // Default pagination size is 10
        Iterable<Category> entities = categoryService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all categories with pagination and size 5", description = "Find all categories with pagination and size 5")
    @GetMapping("/page/{page}/size=5")
    public ResponseEntity<Iterable<Category>> findAllWithPaginationAndSizeFive(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        Iterable<Category> entities = categoryService.getWithPagination(page, 5, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all categories with pagination and size 10", description = "Find all categories with pagination and size 10")
    @GetMapping("/page/{page}/size=10")
    public ResponseEntity<Iterable<Category>> findAllWithPaginationAndSizeTen(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        Iterable<Category> entities = categoryService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all categories with pagination and size 20", description = "Find all categories with pagination and size 20")
    @GetMapping("/page/{page}/size=20")
    public ResponseEntity<Iterable<Category>> findAllWithPaginationAndSizeTwenty(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        Iterable<Category> entities = categoryService.getWithPagination(page, 20, sort);
        return ResponseEntity.ok(entities);
    }
}
