package br.com.lufecrx.crudexercise.api.controller.domain.product;

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

import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.services.domain.product.ProductServicePaginable;

public class ProductControllerPaginableTest {

    @InjectMocks
    private ProductControllerPaginable productControllerPaginable;

    @Mock
    private ProductServicePaginable productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllWithPaginationAndSizeFive() {
        when(productService.getWithPagination(anyInt(), eq(5), any()))
                .thenReturn(Arrays.asList(new Product(), new Product()));
        ResponseEntity<Iterable<Product>> response = productControllerPaginable.findAllWithPaginationAndSizeFive(1, new String[]{"productName", "asc"});
        assertNotNull(response.getBody());
        verify(productService, times(1)).getWithPagination(anyInt(), eq(5), any());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeTen() {
        when(productService.getWithPagination(anyInt(), eq(10), any()))
                .thenReturn(Arrays.asList(new Product(), new Product()));
        ResponseEntity<Iterable<Product>> response = productControllerPaginable.findAllWithPaginationAndSizeTen(1, new String[]{"productName", "asc"});
        assertNotNull(response.getBody());
        verify(productService, times(1)).getWithPagination(anyInt(), eq(10), any());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeTwenty() {
        when(productService.getWithPagination(anyInt(), eq(20), any()))
                .thenReturn(Arrays.asList(new Product(), new Product()));
        ResponseEntity<Iterable<Product>> response = productControllerPaginable.findAllWithPaginationAndSizeTwenty(1, new String[]{"productName", "asc"});
        assertNotNull(response.getBody());
        verify(productService, times(1)).getWithPagination(anyInt(), eq(20), any());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeFiveEmptyResult() {
        when(productService.getWithPagination(anyInt(), eq(5), any())).thenReturn(Collections.emptyList());
        ResponseEntity<Iterable<Product>> response = productControllerPaginable.findAllWithPaginationAndSizeFive(1, new String[]{"productName", "asc"});
        assertNotNull(response.getBody());
        assertTrue(!response.getBody().iterator().hasNext());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeFiveNullResult() {
        when(productService.getWithPagination(anyInt(), eq(5), any())).thenReturn(null);
        ResponseEntity<Iterable<Product>> response = productControllerPaginable.findAllWithPaginationAndSizeFive(1, new String[]{"productName", "asc"});
        assertNull(response.getBody());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeFiveDifferentPage() {
        when(productService.getWithPagination(eq(2), eq(5), any())).thenReturn(Arrays.asList(new Product(), new Product()));
        ResponseEntity<Iterable<Product>> response = productControllerPaginable.findAllWithPaginationAndSizeFive(2, new String[]{"productName", "asc"});
        assertNotNull(response.getBody());
        verify(productService, times(1)).getWithPagination(eq(2), eq(5), any());
    }
}
