package br.com.lufecrx.crudexercise.services.domain.wishlist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.product.ProductNotFoundException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistAlreadyExistsException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistNotFoundException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistsEmptyException;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.repository.WishlistRepository;
import br.com.lufecrx.crudexercise.services.domain.product.ProductService;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Qualifier("standard")
@Slf4j
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductService productService;

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

    public void deleteWishlist(Long wishlistId) {
        log.info("Deleting wishlist with ID {}", wishlistId);

        if (!wishlistRepository.existsById(wishlistId)) {
            throw new WishlistNotFoundException(wishlistId);
        }

        wishlistRepository.deleteById(wishlistId);
    }

    public Iterable<Wishlist> getAllWishlists() {
        log.info("Getting all wishlists");

        if (!wishlistRepository.findAll().iterator().hasNext()) {
            throw new WishlistsEmptyException();
        }

        return wishlistRepository.findAll();
    }

    public Optional<Wishlist> getWishlistById(Long wishlistId) {
        log.info("Getting wishlist by ID {}", wishlistId);

        if (!wishlistRepository.existsById(wishlistId)) {
            throw new WishlistNotFoundException(wishlistId);
        }

        return Optional.of(wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new WishlistNotFoundException(wishlistId)));
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        log.info("Creating wishlist with name {}", wishlist.getName());

        if (wishlistRepository.existsByName(wishlist.getName())) {
            throw new WishlistAlreadyExistsException(wishlist.getName());
        }

        return wishlistRepository.save(wishlist);
    }

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
