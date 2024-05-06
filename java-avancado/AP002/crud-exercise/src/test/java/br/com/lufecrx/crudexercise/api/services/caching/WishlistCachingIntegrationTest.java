package br.com.lufecrx.crudexercise.api.services.caching;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.model.Wishlist;
import br.com.lufecrx.crudexercise.api.repository.WishlistRepository;
import br.com.lufecrx.crudexercise.api.services.domain.wishlist.WishlistService;
import br.com.lufecrx.crudexercise.api.services.domain.wishlist.WishlistServicePaginable;

@SpringBootTest
public class WishlistCachingIntegrationTest {

    @Autowired
    private WishlistServicePaginable wishlistServicePag;

    @Autowired
    private WishlistService wishlistService;

    @SpyBean
    private WishlistRepository wishlistRepository;

    @Autowired
    private CacheManager cacheManager;

    private Faker faker;

    private List<Wishlist> wishlists;

    private List<Product> products;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        products = fillProducts();
        wishlists = fillWishlists();
        wishlistRepository.saveAll(wishlists);
    }

    @AfterEach
    public void tearDown() {
        wishlistRepository.deleteAll();
        cacheManager.getCache("wishlists").clear();
    }

    public List<Product> fillProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            String name = faker.commerce().productName();
            product.setProductName(name);
            product.setPrice(faker.number().randomDouble(2, 1, 1000));
            products.add(product);
        }

        return products;
    }

    public List<Wishlist> fillWishlists() {
        List<Wishlist> wishlists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Wishlist wishlist = new Wishlist();
            String name = faker.commerce().productName();
            wishlist.setName(name);

            for (int j = 0; j < 5; j++) {
                Product product = products.get(j);
                wishlist.addToWishlist(product);
            }

            wishlists.add(wishlist);
        }

        return wishlists;
    }

    @Test
    public void testCaching() {
        // Call the method 100 times
        for (int i = 0; i < 100; i++) {
            wishlistServicePag.getWithPagination(1, 5, new String[] { "name", "asc" });
        }

        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name").ascending());

        // Verify that the method was called only once due to caching
        verify(wishlistRepository, times(1)).findAll(pageRequest);
    }

    @Test
    public void testCacheEvict() {
        // Call the method 100 times
        for (int i = 0; i < 100; i++) {
            wishlistServicePag.getWithPagination(1, 5, new String[] { "name", "asc" });
        }

        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("name").ascending());

        // Verify that the method was called only once due to caching
        verify(wishlistRepository, times(1)).findAll(pageRequest);

        // Delete a wishlist to evict the cache
        wishlistService.deleteWishlist(wishlists.get(0).getId());

        // Call the method again
        wishlistServicePag.getWithPagination(1, 5, new String[] { "name", "asc" });

        // Verify that the method was called again due to cache eviction
        verify(wishlistRepository, times(2)).findAll(pageRequest);
    }

}
