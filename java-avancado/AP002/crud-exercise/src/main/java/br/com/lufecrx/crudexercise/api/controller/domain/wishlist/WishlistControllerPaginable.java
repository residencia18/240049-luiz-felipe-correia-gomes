package br.com.lufecrx.crudexercise.api.controller.domain.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.api.model.Wishlist;
import br.com.lufecrx.crudexercise.api.services.domain.wishlist.WishlistServicePaginable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/paginable/wishlists")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Wishlists found"),
    @ApiResponse(responseCode = "404", description = "Wishlists not found")
})
public class WishlistControllerPaginable {

    private final WishlistServicePaginable wishlistService;

    @Autowired
    public WishlistControllerPaginable(@Qualifier("paginable") WishlistServicePaginable wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Operation(summary = "Find all wishlists with pagination", description = "Find all wishlists with pagination")
    @GetMapping("/page/{page}")
    public ResponseEntity<Iterable<Wishlist>> findAll(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        // Default pagination size is 10
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all wishlists with pagination and size 5", description = "Find all wishlists with pagination and size 5")
    @GetMapping("/page/{page}/size=5")
    public ResponseEntity<Iterable<Wishlist>> findAllWithPaginationAndSizeFive(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 5, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all wishlists with pagination and size 10", description = "Find all wishlists with pagination and size 10")
    @GetMapping("/page/{page}/size=10")
    public ResponseEntity<Iterable<Wishlist>> findAllWithPaginationAndSizeTen(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all wishlists with pagination and size 20", description = "Find all wishlists with pagination and size 20")
    @GetMapping("/page/{page}/size=20")
    public ResponseEntity<Iterable<Wishlist>> findAllWithPaginationAndSizeTwenty(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        Iterable<Wishlist> entities = wishlistService.getWithPagination(page, 20, sort);
        return ResponseEntity.ok(entities);
    }
}
