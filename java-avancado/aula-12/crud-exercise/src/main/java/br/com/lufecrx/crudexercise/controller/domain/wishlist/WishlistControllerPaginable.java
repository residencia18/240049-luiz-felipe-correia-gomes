package br.com.lufecrx.crudexercise.controller.domain.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.services.domain.wishlist.WishlistServicePaginable;

@RestController
@RequestMapping("/paginable/wishlists")
public class WishlistControllerPaginable {
    
    private WishlistServicePaginable wishlistService;

    @Autowired
    public WishlistControllerPaginable(@Qualifier("paginable") WishlistServicePaginable wishlistService) {
        this.wishlistService = wishlistService;
    }   
    
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
