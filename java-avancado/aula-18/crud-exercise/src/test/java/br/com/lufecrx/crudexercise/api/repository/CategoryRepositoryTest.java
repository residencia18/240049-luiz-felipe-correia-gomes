package br.com.lufecrx.crudexercise.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.api.model.Category;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Category category;

    private Faker faker;

    private Category generateFakerCategory() {
        Category category = new Category();
        category.setName(faker.commerce().department());

        return category;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        category = generateFakerCategory();
        entityManager.persistAndFlush(category);
    }

    @Test
    public void testSaveCategory() {
        Category newCategory = generateFakerCategory();
        Category savedCategory = categoryRepository.save(newCategory);
        assertThat(savedCategory).isNotNull();
    }

    @Test
    public void testFindAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        assertThat(categories).isNotEmpty();
    }

    @Test
    public void testFindCategoryById() {
        Optional<Category> foundCategory = categoryRepository.findById(category.getId());
        assertThat(foundCategory).isPresent().hasValue(category);
    }

    @Test
    public void testUpdateCategory() {
        String updatedName = faker.commerce().department();
        category.setName(updatedName);
        categoryRepository.save(category);
        Optional<Category> updatedCategory = categoryRepository.findById(category.getId());
        assertThat(updatedCategory).isPresent().hasValueSatisfying(c -> assertThat(c.getName()).isEqualTo(updatedName));
    }

    @Test
    public void testDeleteCategory() {
        categoryRepository.delete(category);
        Optional<Category> deletedCategory = categoryRepository.findById(category.getId());
        assertThat(deletedCategory).isNotPresent();
    }
}