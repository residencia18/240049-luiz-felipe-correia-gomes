package br.com.lufecrx.crudexercise.api.services.domain.wishlist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.model.Wishlist;
import br.com.lufecrx.crudexercise.api.model.dto.WishlistDTO;
import br.com.lufecrx.crudexercise.api.repository.WishlistRepository;
import br.com.lufecrx.crudexercise.api.services.domain.product.ProductService;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.exceptions.api.domain.product.ProductNotFoundException;
import br.com.lufecrx.crudexercise.exceptions.api.domain.wishlist.WishlistAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.api.domain.wishlist.WishlistNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class WishlistService {
    // TODO: Update the service 

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductService productService;

    @CacheEvict(value = "wishlists", allEntries = true)
    public void addProductToWishlist(Long wishlistId, Long productId) {
        log.info("Adding product with ID {} to wishlist with ID {}", productId, wishlistId);

        Optional<Wishlist> optWishlist = wishlistRepository.findById(wishlistId);
        Optional<Product> optProduct = productService.getProductById(productId);

        if (optWishlist.isEmpty()) {
            log.error("Wishlist with ID {} not found.", wishlistId);
            throw new WishlistNotFoundException(wishlistId);
        }

        if (optProduct.isEmpty()) {
            log.error("Product with ID {} not found.", productId);
            throw new ProductNotFoundException(productId);
        }

        Wishlist wishlist = optWishlist.get();
        Product product = optProduct.get();

        wishlist.addToWishlist(product);
        wishlistRepository.save(wishlist);
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public void removeProductFromWishlist(Long wishlistId, Long productId) {
        log.info("Removing product with ID {} from wishlist with ID {}", productId, wishlistId);

        Optional<Wishlist> optWishlist = wishlistRepository.findById(wishlistId);
        Optional<Product> optProduct = productService.getProductById(productId);

        if (optWishlist.isPresent() && optProduct.isPresent()) {
            Wishlist wishlist = optWishlist.get();
            Product product = optProduct.get();

            wishlist.removeFromWishlist(product);
            wishlistRepository.save(wishlist);
        }
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public void deleteWishlist(Long wishlistId) {
        log.info("Deleting wishlist with ID {}", wishlistId);

        // Verify if the wishlist exists
        if (!wishlistRepository.existsById(wishlistId)) {
            throw new WishlistNotFoundException(wishlistId);
        }

        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verify if the wishlist belongs to the authenticated user
        if (!wishlistRepository.findById(wishlistId).get().getUser().equals(user)) {
            throw new WishlistNotFoundException(wishlistId);
        }

        wishlistRepository.deleteById(wishlistId);
    }

    // @Cacheable(value = "wishlists")
    // public Iterable<Wishlist> getAllWishlists() {
    //     log.info("Getting all wishlists");

    //     if (!wishlistRepository.findAll().iterator().hasNext()) {
    //         throw new WishlistsEmptyException();
    //     }

    //     return wishlistRepository.findAll();
    // }

    @Cacheable(value = "wishlists", key = "#wishlistId")
    public Optional<Wishlist> getWishlistById(Long wishlistId) {
        log.info("Getting wishlist by ID {}", wishlistId);

        
        // Verify if the wishlist exists
        if (!wishlistRepository.existsById(wishlistId)) {
            throw new WishlistNotFoundException(wishlistId);
        
        }

        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verify if the wishlist belongs to the authenticated user
        if (!wishlistRepository.findById(wishlistId).get().getUser().equals(user)) {
            throw new WishlistNotFoundException(wishlistId);
        }

        // Return the wishlist
        return Optional.of(wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new WishlistNotFoundException(wishlistId)));
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public Wishlist createWishlist(WishlistDTO wishlist) {
        log.info("Creating wishlist with name {}", wishlist.name());

        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verify if the authenticated user already has a wishlist with the same name
        if (wishlistRepository.existsByNameAndUser(wishlist.name(), user)) {
            throw new WishlistAlreadyExistsException(wishlist.name());
        }

        // Create the new wishlist and set the authenticated user
        Wishlist newWishlist = Wishlist.builder()
                            .name(wishlist.name())
                            .build();
        newWishlist.setUser(user);

        return wishlistRepository.save(newWishlist);
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public Wishlist updateWishlist(Long wishlistId, Wishlist updatedWishlist) {
        log.info("Updating wishlist with ID {}", wishlistId);

        Optional<Wishlist> existingWishlist = wishlistRepository.findById(wishlistId);
        if (existingWishlist.isPresent()) {
            Wishlist wishlist = existingWishlist.get();
            wishlist.setName(updatedWishlist.getName());
            return wishlistRepository.save(wishlist);
        }
        throw new WishlistNotFoundException(wishlistId);
    }
}
