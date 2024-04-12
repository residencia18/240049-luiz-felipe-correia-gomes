package br.com.lufecrx.crudexercise.controller.domain.category;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.services.domain.category.CategoryServicePaginable;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paginable/categories")
public class CategoryControllerPaginable extends CategoryController {

    @Qualifier("paginable")
    private CategoryServicePaginable categoryService;

    @GetMapping("/page/{page}/size/5")
    public ResponseEntity<Iterable<Category>> findAllWithPaginationAndSizeFive(@PathVariable int page) {
        Iterable<Category> entities = categoryService.getWithPagination(page, 5); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/10")
    public ResponseEntity<Iterable<Category>> findAllWithPaginationAndSizeTen(@PathVariable int page) {
        Iterable<Category> entities = categoryService.getWithPagination(page, 10); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/20")
    public ResponseEntity<Iterable<Category>> findAllWithPaginationAndSizeTwenty(@PathVariable int page) {
        Iterable<Category> entities = categoryService.getWithPagination(page, 20); 
        return ResponseEntity.ok(entities);
    }
}
