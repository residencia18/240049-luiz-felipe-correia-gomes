package br.com.lufecrx.crudexercise.api.controller.domain.wishlist;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.api.model.dto.WishlistDTO;
import br.com.lufecrx.crudexercise.api.services.domain.wishlist.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "You are not authorized to access this resource"),
        @ApiResponse(responseCode = "404", description = "Wishlist not found"),
        @ApiResponse(responseCode = "409", description = "Wishlist already exists"),
})
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(@Qualifier("standard") WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    // @GetMapping
    // public ResponseEntity<Iterable<Wishlist>> findAll() {
    // Iterable<Wishlist> entities = wishlistService.getAllWishlists();
    // return ResponseEntity.ok(entities);
    // }

    @Operation(summary = "Find a wishlist by its ID", description = "Find a wishlist by its ID")
    @ApiResponse(responseCode = "200", description = "Wishlist found by its ID")
    @GetMapping("/find-wishlist/{id}")
    public ResponseEntity<Optional<WishlistDTO>> findById(@PathVariable Long id) {
        Optional<WishlistDTO> entity = wishlistService.getWishlistById(id);
        return ResponseEntity.ok(entity);
    }

    @Operation(summary = "Find a wishlist by its name", description = "Find a wishlist by its name")
    @ApiResponse(responseCode = "200", description = "Wishlist found by its name")
    @GetMapping("/find-wishlist-by-name/{name}")
    public ResponseEntity<Optional<WishlistDTO>> findByName(@PathVariable String name) {
        Optional<WishlistDTO> entity = wishlistService.getWishlistByName(name);
        return ResponseEntity.ok(entity);
    }

    @Operation(summary = "Create a new wishlist", description = "Create a new wishlist with the given data")
    @ApiResponse(responseCode = "200", description = "Wishlist created")
    @PostMapping("/add-wishlist")
    public ResponseEntity<String> save(@RequestBody @Valid WishlistDTO dto) {
        wishlistService.createWishlist(dto);
        return ResponseEntity.ok(bundle.getString("wishlist.successfully_created"));
    }

    @Operation(summary = "Add a product to a wishlist", description = "Add a product to a wishlist with the given data")
    @ApiResponse(responseCode = "200", description = "Product added to wishlist")
    @PutMapping("/{wistlistId}/add-product/{productId}")
    public ResponseEntity<String> addProduct(@PathVariable Long wistlistId, @PathVariable Long productId) {
        wishlistService.addProductToWishlist(wistlistId, productId);
        return ResponseEntity.ok(bundle.getString("wishlist.successfully_added_product"));
    }

    @Operation(summary = "Remove a product from a wishlist", description = "Remove a product from a wishlist with the given data")
    @ApiResponse(responseCode = "200", description = "Product removed to wishlist")
    @DeleteMapping("/{wihslistId}/remove-product/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable Long wishlistId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(wishlistId, productId);
        return ResponseEntity.ok(bundle.getString("wishlist.successfully_removed_product"));
    }

    @Operation(summary = "Update a wishlist", description = "Update a wishlist with the given data")
    @ApiResponse(responseCode = "200", description = "Wishlist updated")
    @PutMapping("/update-wishlist/{wishlistActualId}")
    public ResponseEntity<String> update(@PathVariable Long wishlistActualId,
            @RequestBody @Valid WishlistDTO wishlistUpdated) {
        wishlistService.updateWishlist(wishlistActualId, wishlistUpdated);
        return ResponseEntity.ok(bundle.getString("wishlist.successfully_updated"));
    }

    @Operation(summary = "Delete a wishlist", description = "Delete a wishlist by its ID")
    @ApiResponse(responseCode = "200", description = "Wishlist deleted")
    @DeleteMapping("/delete-wishlist/{wishlistId}")
    public ResponseEntity<String> delete(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
        return ResponseEntity.ok(bundle.getString("wishlist.successfully_deleted"));
    }
}
