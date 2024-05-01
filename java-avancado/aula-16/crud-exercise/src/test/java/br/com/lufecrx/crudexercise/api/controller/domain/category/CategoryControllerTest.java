package br.com.lufecrx.crudexercise.api.controller.domain.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.category.CategoriesEmptyException;
import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.category.CategoryAlreadyExistsException;
import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.category.CategoryNotFoundException;
import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.services.domain.category.CategoryService;
import br.com.lufecrx.crudexercise.api.services.domain.category.CategoryServicePaginable;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @InjectMocks
    private CategoryControllerPaginable categoryControllerPaginable;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryServicePaginable categoryServicePaginable;

    private List<Category> categories;

    private Faker faker;

    private ResourceBundle bundle;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        categories = fillCategories();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
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
        when(categoryServicePaginable.getWithPagination(anyInt(), anyInt(), any())).thenReturn(categories);

        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAll(1, new String[]{"name", "asc"});

        assertEquals(10, ((Collection<?>) response.getBody()).size());
        verify(categoryServicePaginable, times(1)).getWithPagination(1, 10, new String[]{"name", "asc"});
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

        assertEquals(bundle.getString("category.successfully_created"), response.getBody());
        verify(categoryService, times(1)).createCategory(category);
    }

    @Test
    public void testUpdate() {
        Category category = categories.get(8);
        when(categoryService.updateCategory(anyLong(), any(Category.class))).thenReturn(category);

        ResponseEntity<String> response = categoryController.update(category, 8L);

        assertEquals(bundle.getString("category.successfully_updated"), response.getBody());
        verify(categoryService, times(1)).updateCategory(8L, category);
    }

    @Test
    public void testDelete() {
        doNothing().when(categoryService).deleteCategory(anyLong());

        ResponseEntity<String> response = categoryController.delete(1L);

        assertEquals(bundle.getString("category.successfully_deleted"), response.getBody());
        verify(categoryService, times(1)).deleteCategory(1L);
    }

    @Test
    public void testWhenCategoryAlreadyExists() {
        Category category = categories.get(2);
        when(categoryService.createCategory(category)).thenThrow(new CategoryAlreadyExistsException(category.getName()));

        Exception exception = assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryController.save(category);
        });
    
        String expectedMessage = bundle.getString("category.already_exists").replace("{name}", category.getName());
        String actualMessage = exception.getMessage();
    
        assertEquals(expectedMessage, actualMessage);
    }	

    @Test
    public void testWhenCategoryNotFound() {
        when(categoryService.getCategoryById(anyLong())).thenThrow(new CategoryNotFoundException(1L));
    
        Exception exception = assertThrows(CategoryNotFoundException.class, () -> {
            categoryController.findById(1L);
        });
    
        String expectedMessage = bundle.getString("category.not_found").replace("{id}", "1");
        String actualMessage = exception.getMessage();
    
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenCategoriesEmpty() {
        when(categoryServicePaginable.getWithPagination(anyInt(), anyInt(), any())).thenThrow(new CategoriesEmptyException());
    
        Exception exception = assertThrows(CategoriesEmptyException.class, () -> {
            categoryControllerPaginable.findAll(1, new String[]{"name", "asc"});
        });
    
        String expectedMessage = bundle.getString("category.empty_list");
        String actualMessage = exception.getMessage();
    
        assertEquals(expectedMessage, actualMessage);
    }
}