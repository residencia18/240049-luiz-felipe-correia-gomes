package br.com.lufecrx.crudexercise.controller.domain.wishlist;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.services.domain.wishlist.WishlistServicePaginable;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paginable/wishlists")
public class WishlistControllerPaginable extends WishlistController {
    
    @Qualifier("paginable")
    private WishlistServicePaginable wishlistService;

    @GetMapping("/page/{page}/size/5")
    public ResponseEntity<Iterable<Wishlist>> findAllWithPaginationAndSizeFive(@PathVariable int page) {
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 5); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/10")
    public ResponseEntity<Iterable<Wishlist>> findAllWithPaginationAndSizeTen(@PathVariable int page) {
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 10); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/20")
    public ResponseEntity<Iterable<Wishlist>> findAllWithPaginationAndSizeTwenty(@PathVariable int page) {
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 20); 
        return ResponseEntity.ok(entities);
    }
}
