package br.com.lufecrx.crudexercise.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.services.CategoryService;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private List<Category> categories;

    private Faker faker;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        categories = fillCategories();
    }

    public List<Category> fillCategories() {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            String name = faker.commerce().department();
            category.setName(name);

            categories.add(category);
        }

        return categories;
    }

    @Test
    public void testFindAll() {
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<Iterable<Category>> response = categoryController.findAll();

        assertEquals(10, ((Collection<?>) response.getBody()).size());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    public void testFindById() {
        Category category = categories.get(4);
        when(categoryService.getCategoryById(anyLong())).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.findById(4L);

        assertEquals(category.getName(), response.getBody().getName());
        verify(categoryService, times(1)).getCategoryById(4L);
    }

    @Test
    public void testSave() {
        Category category = categories.get(5);
        when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        ResponseEntity<String> response = categoryController.save(category);

        assertEquals("Category created successfully.", response.getBody());
        verify(categoryService, times(1)).createCategory(category);
    }

    @Test
    public void testUpdate() {
        Category category = categories.get(8);
        when(categoryService.updateCategory(anyLong(), any(Category.class))).thenReturn(category);

        ResponseEntity<String> response = categoryController.update(category, 8L);

        assertEquals("Category updated successfully.", response.getBody());
        verify(categoryService, times(1)).updateCategory(8L, category);
    }

    @Test
    public void testDelete() {
        doNothing().when(categoryService).deleteCategory(anyLong());

        ResponseEntity<String> response = categoryController.delete(1L);

        assertEquals("Category deleted successfully.", response.getBody());
        verify(categoryService, times(1)).deleteCategory(1L);
    }
}