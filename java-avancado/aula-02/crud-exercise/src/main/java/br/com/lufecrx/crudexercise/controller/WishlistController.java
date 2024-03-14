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
import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.repository.WishlistRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {
    
    private final WishlistRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Wishlist>> findAll() {
        Iterable <Wishlist> dto = repository.findAll();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Wishlist> save(@RequestBody @Valid Wishlist dto) {
        return ResponseEntity.ok(repository.save(dto));
    }

    @PostMapping("/add-product/{id}")
    public ResponseEntity<Wishlist> addProduct(@RequestBody @Valid ProductModel product, @PathVariable Long id) {
        Optional <Wishlist> opt = repository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Wishlist wishlist = opt.get();
        wishlist.addToWishlist(product);

        return ResponseEntity.ok(repository.save(wishlist));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Wishlist> update(@RequestBody @Valid Wishlist dto, @PathVariable Long id) {
        Optional <Wishlist> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Wishlist entity = opt.get();
        entity.setName(dto.getName());
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
