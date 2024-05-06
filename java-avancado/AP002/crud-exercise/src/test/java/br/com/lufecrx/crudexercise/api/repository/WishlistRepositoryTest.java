package br.com.lufecrx.crudexercise.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.api.model.Wishlist;

@DataJpaTest
public class WishlistRepositoryTest {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Wishlist wishlist;

    private Faker faker;

    private Wishlist generateFakerWishlist() {
        Wishlist wishlist = new Wishlist();
        wishlist.setName(faker.commerce().department());

        return wishlist;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        wishlist = generateFakerWishlist();
        entityManager.persistAndFlush(wishlist);
    }

    @Test
    public void testSaveWishlist() {
        Wishlist newWishlist = generateFakerWishlist();
        Wishlist savedWishlist = wishlistRepository.save(newWishlist);
        assertThat(savedWishlist).isNotNull();
    }

    @Test
    public void testFindAllWishlists() {
        Iterable<Wishlist> wishlists = wishlistRepository.findAll();
        assertThat(wishlists).isNotEmpty();
    }

    @Test
    public void testFindWishlistById() {
        Optional<Wishlist> foundWishlist = wishlistRepository.findById(wishlist.getId());
        assertThat(foundWishlist).isPresent().hasValue(wishlist);
    }

    @Test
    public void testUpdateWishlist() {
        String updatedName = faker.commerce().department();
        wishlist.setName(updatedName);
        wishlistRepository.save(wishlist);
        Optional<Wishlist> updatedWishlist = wishlistRepository.findById(wishlist.getId());
        assertThat(updatedWishlist).isPresent().hasValueSatisfying(w -> assertThat(w.getName()).isEqualTo(updatedName));
    }

    @Test
    public void testDeleteWishlist() {
        wishlistRepository.delete(wishlist);
        Optional<Wishlist> deletedWishlist = wishlistRepository.findById(wishlist.getId());
        assertThat(deletedWishlist).isNotPresent();
    }
}