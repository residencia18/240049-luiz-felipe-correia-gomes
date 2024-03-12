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

import br.com.lufecrx.crudexercise.model.EntityExampleModel;
import br.com.lufecrx.crudexercise.repository.EntityExampleRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/crud-examples")
public class EntityExampleController {

    private final EntityExampleRepository repository;

    @PostMapping
    public ResponseEntity<EntityExampleModel> save(@RequestBody EntityExampleModel dto) {
        if (!dto.nameExampleIsValid()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.save(dto));
    }

    @GetMapping
    public ResponseEntity<Iterable<EntityExampleModel>> findAll() {
        Iterable <EntityExampleModel> entityExample = repository.findAll();
        if (entityExample == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entityExample);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityExampleModel> findById(@PathVariable Long id) {
        Optional <EntityExampleModel> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        EntityExampleModel dto = opt.get();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityExampleModel> update(@PathVariable Long id) {
        Optional <EntityExampleModel> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!opt.get().nameExampleIsValid()) {
            return ResponseEntity.badRequest().build();
        }
        EntityExampleModel entity = repository.save(opt.get());
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional <EntityExampleModel> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(opt.get());
        return ResponseEntity.ok().build();
    }

}
