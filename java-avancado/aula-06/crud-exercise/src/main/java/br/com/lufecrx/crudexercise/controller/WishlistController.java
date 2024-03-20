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

import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.repository.WishlistRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/wishlist")
public class WishlistController {
    
    private final WishlistRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Wishlist>> findAll() {
        log.info("Searching for all wishlists");
        Iterable <Wishlist> dto = repository.findAll();
        
        if (dto == null) {
            log.info("No wishlists found.");
            return ResponseEntity.notFound().build();
        }

        log.info("Wishlists found successfully.");
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Wishlist> save(@RequestBody @Valid Wishlist dto) {
        log.info("Saving wishlist with name {}", dto.getName());
        return ResponseEntity.ok(repository.save(dto));
    }

    @PostMapping("/add-product/{id}")
    public ResponseEntity<Wishlist> addProduct(@RequestBody @Valid Product product, @PathVariable Long id) {
        log.info("Adding product with name {} to wishlist with id {}", product.getProductName(), id);

        Optional <Wishlist> opt = repository.findById(id);

        if (opt.isEmpty()) {
            log.error("Wishlist with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }

        Wishlist wishlist = opt.get();
        wishlist.addToWishlist(product);

        log.info("Product with name {} added to wishlist with id {} successfully.", product.getProductName(), id);
        return ResponseEntity.ok(repository.save(wishlist));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Wishlist> update(@RequestBody @Valid Wishlist dto, @PathVariable Long id) {
        log.info("Updating wishlist with id {}", id);
        
        Optional <Wishlist> opt = repository.findById(id);

        if (opt.isEmpty()) {
            log.error("Wishlist with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }

        
        Wishlist entity = opt.get();
        
        log.info("Old wishlist name: {}", entity.getName());
        log.info("New wishlist name: {}", dto.getName());
        
        entity.setName(dto.getName());
 
        log.info("Wishlist with id {} updated successfully.", id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting wishlist with id {}", id);

        repository.deleteById(id);

        log.info("Wishlist with id {} deleted successfully.", id);
        return ResponseEntity.ok().build();
    }
}
