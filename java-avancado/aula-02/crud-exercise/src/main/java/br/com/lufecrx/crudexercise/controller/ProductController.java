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

import br.com.lufecrx.crudexercise.model.ProductModel;
import br.com.lufecrx.crudexercise.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    
    @PostMapping
    public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductModel dto) {
        if (!dto.productNameIsValid()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.save(dto));
    }

    @GetMapping
    public ResponseEntity<Iterable<ProductModel>> findAll() {
        Iterable <ProductModel> entityExample = repository.findAll();
        if (entityExample == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entityExample);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Long id) {
        Optional <ProductModel> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProductModel dto = opt.get();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable Long id, @RequestBody @Valid ProductModel dto) {
        Optional <ProductModel> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!opt.get().productNameIsValid()) {
            return ResponseEntity.badRequest().build();
        }
        
        ProductModel entity = opt.get();
        
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional <ProductModel> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(opt.get());
        return ResponseEntity.ok().build();
    }

}
