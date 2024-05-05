package br.com.lufecrx.crudexercise.api.controller.domain.category;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.services.domain.category.CategoryServicePaginable;

public class CategoryControllerPaginableTest {

    @InjectMocks
    private CategoryControllerPaginable categoryControllerPaginable;

    @Mock
    private CategoryServicePaginable categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllWithPaginationAndSizeFive() {
        when(categoryService.getWithPagination(anyInt(), eq(5), any()))
                .thenReturn(Arrays.asList(new Category(), new Category()));
        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAllWithPaginationAndSizeFive(1,
                new String[] { "name", "asc" });
        assertNotNull(response.getBody());
        verify(categoryService, times(1)).getWithPagination(anyInt(), eq(5), any());
    }

    @Test
    public void testFindAllWithPaginationAndSizeTen() {
        when(categoryService.getWithPagination(anyInt(), eq(10), any()))
                .thenReturn(Arrays.asList(new Category(), new Category()));
        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAllWithPaginationAndSizeTen(1,
                new String[] { "name", "asc" });
        assertNotNull(response.getBody());
        verify(categoryService, times(1)).getWithPagination(anyInt(), eq(10), any());
    }

    @Test
    public void testFindAllWithPaginationAndSizeTwenty() {
        when(categoryService.getWithPagination(anyInt(), eq(20), any()))
                .thenReturn(Arrays.asList(new Category(), new Category()));
        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAllWithPaginationAndSizeTwenty(1,
                new String[] { "name", "asc" });
        assertNotNull(response.getBody());
        verify(categoryService, times(1)).getWithPagination(anyInt(), eq(20), any());
    }

    @Test
    public void testFindAllWithPaginationAndSizeFiveEmptyResult() {
        when(categoryService.getWithPagination(anyInt(), eq(5), any())).thenReturn(Collections.emptyList());
        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAllWithPaginationAndSizeFive(1,
                new String[] { "name", "asc" });
        assertNotNull(response.getBody());
        assertTrue(!response.getBody().iterator().hasNext());
    }

    @Test
    public void testFindAllWithPaginationAndSizeFiveNullResult() {
        when(categoryService.getWithPagination(anyInt(), eq(5), any())).thenReturn(null);
        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAllWithPaginationAndSizeFive(1,
                new String[] { "name", "asc" });
        assertNull(response.getBody());
    }

    @Test
    public void testFindAllWithPaginationAndSizeFiveDifferentPage() {
        when(categoryService.getWithPagination(eq(2), eq(5), any()))
                .thenReturn(Arrays.asList(new Category(), new Category()));
        ResponseEntity<Iterable<Category>> response = categoryControllerPaginable.findAllWithPaginationAndSizeFive(2,
                new String[] { "name", "asc" });
        assertNotNull(response.getBody());
        verify(categoryService, times(1)).getWithPagination(eq(2), eq(5), any());
    }
}
