package br.com.lufecrx.crudexercise.api.controller.domain.wishlist;

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

import br.com.lufecrx.crudexercise.api.model.Wishlist;
import br.com.lufecrx.crudexercise.api.services.domain.wishlist.WishlistServicePaginable;

public class WishlistControllerPaginableTest {

    @InjectMocks
    private WishlistControllerPaginable wishlistControllerPaginable;

    @Mock
    private WishlistServicePaginable wishlistService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllWithPaginationAndSizeFive() {
        when(wishlistService.getWithPagination(anyInt(), eq(5), any()))
                .thenReturn(Arrays.asList(new Wishlist(), new Wishlist()));
        ResponseEntity<Iterable<Wishlist>> response = wishlistControllerPaginable.findAllWithPaginationAndSizeFive(1, new String[]{"name", "asc"});
        assertNotNull(response.getBody());
        verify(wishlistService, times(1)).getWithPagination(anyInt(), eq(5), any());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeTen() {
        when(wishlistService.getWithPagination(anyInt(), eq(10), any()))
                .thenReturn(Arrays.asList(new Wishlist(), new Wishlist()));
        ResponseEntity<Iterable<Wishlist>> response = wishlistControllerPaginable.findAllWithPaginationAndSizeTen(1, new String[]{"name", "asc"});
        assertNotNull(response.getBody());
        verify(wishlistService, times(1)).getWithPagination(anyInt(), eq(10), any());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeTwenty() {
        when(wishlistService.getWithPagination(anyInt(), eq(20), any()))
                .thenReturn(Arrays.asList(new Wishlist(), new Wishlist()));
        ResponseEntity<Iterable<Wishlist>> response = wishlistControllerPaginable.findAllWithPaginationAndSizeTwenty(1, new String[]{"name", "asc"});
        assertNotNull(response.getBody());
        verify(wishlistService, times(1)).getWithPagination(anyInt(), eq(20), any());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeFiveEmptyResult() {
        when(wishlistService.getWithPagination(anyInt(), eq(5), any())).thenReturn(Collections.emptyList());
        ResponseEntity<Iterable<Wishlist>> response = wishlistControllerPaginable.findAllWithPaginationAndSizeFive(1, new String[]{"name", "asc"});
        assertNotNull(response.getBody());
        assertTrue(!response.getBody().iterator().hasNext());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeFiveNullResult() {
        when(wishlistService.getWithPagination(anyInt(), eq(5), any())).thenReturn(null);
        ResponseEntity<Iterable<Wishlist>> response = wishlistControllerPaginable.findAllWithPaginationAndSizeFive(1, new String[]{"name", "asc"});
        assertNull(response.getBody());
    }
    
    @Test
    public void testFindAllWithPaginationAndSizeFiveDifferentPage() {
        when(wishlistService.getWithPagination(eq(2), eq(5), any())).thenReturn(Arrays.asList(new Wishlist(), new Wishlist()));
        ResponseEntity<Iterable<Wishlist>> response = wishlistControllerPaginable.findAllWithPaginationAndSizeFive(2, new String[]{"name", "asc"});
        assertNotNull(response.getBody());
        verify(wishlistService, times(1)).getWithPagination(eq(2), eq(5), any());
    }
}
