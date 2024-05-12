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
import br.com.lufecrx.crudexercise.api.repository.ProductRepository;
import br.com.lufecrx.crudexercise.api.repository.WishlistRepository;
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

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @CacheEvict(value = "wishlists", allEntries = true)
    public void addProductToWishlist(Long wishlistId, Long productId) {
        log.info("Adding product with ID {} to wishlist with ID {}", productId, wishlistId);

        // Get the authenticated user and verify if the wishlist and product exist
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verify if the wishlist exists and belongs to the authenticated user
        Optional<Wishlist> wishlist = wishlistRepository.findByIdAndUser(productId, user);
        if (wishlist.isEmpty()) {
            throw new WishlistNotFoundException(wishlistId);
        }

        // Verify if the product exists
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        // Add the product to the wishlist and save it
        wishlist.get().addToWishlist(product.get());
        wishlistRepository.save(wishlist.get());
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public void removeProductFromWishlist(Long wishlistId, Long productId) {
        log.info("Removing product with ID {} from wishlist with ID {}", productId, wishlistId);

        // Get the authenticated user and verify if the wishlist and product exist
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verify if the wishlist exists and belongs to the authenticated user
        Optional<Wishlist> wishlist = wishlistRepository.findByIdAndUser(productId, user);
        if (wishlist.isEmpty()) {
            throw new WishlistNotFoundException(wishlistId);
        }

        // Verify if the product exists
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        // Remove the product from the wishlist and save it
        wishlist.get().removeFromWishlist(product.get());
        wishlistRepository.save(wishlist.get());
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public void deleteWishlist(Long wishlistId) {
        log.info("Deleting wishlist with ID {}", wishlistId);

        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Wishlist> wishlist = wishlistRepository.findByIdAndUser(wishlistId, user);
        if (wishlist.isPresent()) {
            // Delete the wishlist if it exists and belongs to the authenticated user
            wishlistRepository.delete(wishlist.get());
        } else {
            // Throw an exception if the wishlist is not found
            throw new WishlistNotFoundException(wishlistId);
        }

    }

    // @Cacheable(value = "wishlists")
    // public Iterable<Wishlist> getAllWishlists() {
    // log.info("Getting all wishlists");

    // if (!wishlistRepository.findAll().iterator().hasNext()) {
    // throw new WishlistsEmptyException();
    // }

    // return wishlistRepository.findAll();
    // }

    @Cacheable(value = "wishlists", key = "#wishlistId")
    public Optional<WishlistDTO> getWishlistById(Long wishlistId) {
        log.info("Getting wishlist by ID {}", wishlistId);

        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Wishlist> wishlist = wishlistRepository.findByIdAndUser(wishlistId, user);

        if (wishlist.isPresent()) {
            // Return the wishlist if it exists and belongs to the authenticated user
            return Optional.of(WishlistDTO.from(wishlist.get()));
        }

        // Throw an exception if the wishlist is not found
        throw new WishlistNotFoundException(wishlistId);
    }

    @Cacheable(value = "wishlists", key = "#name")
    public Optional<WishlistDTO> getWishlistByName(String name) {
        log.info("Getting wishlist by name {}", name);

        // Get the authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Wishlist> wishlist = wishlistRepository.findByNameAndUser(name, user);

        if (wishlist.isPresent()) {
            // Return the wishlist if it exists and belongs to the authenticated user
            return Optional.of(WishlistDTO.from(wishlist.get()));
        }

        // Throw an exception if the wishlist is not found
        throw new WishlistNotFoundException(name);
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public void createWishlist(WishlistDTO wishlist) {
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

        // Save the new wishlist
        wishlistRepository.save(newWishlist);
    }

    @CacheEvict(value = "wishlists", allEntries = true)
    public void renameWishlist(Long wishlistId, WishlistDTO updatedWishlist) {
        log.info("Updating wishlist with ID {}", wishlistId);

        // Get the authenticated user and verify if the wishlist belongs to the
        // authenticated user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Wishlist> wishlist = wishlistRepository.findByIdAndUser(wishlistId, user);
        if (wishlist.isPresent()) {
        // Verify if the wishlist exists and belongs to the authenticated user

            if (wishlistRepository.existsByNameAndUser(updatedWishlist.name(), user)) {
                // Verify if the authenticated user already has a wishlist with the same name
                throw new WishlistAlreadyExistsException(updatedWishlist.name());
            }

            // Rename the wishlist
            wishlist.get().setName(updatedWishlist.name());
            wishlistRepository.save(wishlist.get());

        } else {
            // Return an exception if the wishlist does not exist
            throw new WishlistNotFoundException(wishlistId);
        }

    }
}
