package br.com.lufecrx.crudexercise.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.model.Product;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Product product;

    private Faker faker;

    private Product generateFakerProduct() {
        Product product = new Product();
        product.setProductName(faker.commerce().productName());
        product.setPrice(faker.number().randomDouble(2, 1, 1000));

        return product;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        product = generateFakerProduct();
        entityManager.persistAndFlush(product);
    }

    @Test
    public void testSaveProduct() {
        Product newProduct = generateFakerProduct();
        productRepository.save(newProduct);
        assertThat(productRepository.findById(newProduct.getId())).isPresent();
    }

    @Test
    public void testFindAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1).contains(product);
    }

    @Test
    public void testFindProductById() {
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent().contains(product);
    }

    @Test
    public void testUpdateProduct() {
        String updatedProductName = faker.commerce().productName();
        product.setProductName(updatedProductName);
        productRepository.save(product);
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getProductName()).isEqualTo(updatedProductName);
    }

    @Test
    public void testDeleteProduct() {
        productRepository.delete(product);
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isNotPresent();
    }
}