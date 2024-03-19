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

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        Iterable <Category> entities = repository.findAll();
        if (entities == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional <Category> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category dto = opt.get();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody @Valid Category dto) {
        return ResponseEntity.ok(repository.save(dto));   
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid Category dto, @PathVariable Long id) {
        Optional <Category> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category entity = opt.get();
        entity.setCategoryName(dto.getCategoryName());
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional <Category> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(opt.get());
        return ResponseEntity.ok().build();
    }

}
