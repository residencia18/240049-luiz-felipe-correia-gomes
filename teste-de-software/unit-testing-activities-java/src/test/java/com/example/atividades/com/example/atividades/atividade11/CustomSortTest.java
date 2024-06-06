package com.example.atividades.atividade11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CustomSortTest {

    @Test
    public void testCustomSortAscendingOrder() {
        CustomSort sorter = new CustomSort();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(expected, sorter.customSort(numbers));
    }

    @Test
    public void testCustomSortDescendingOrder() {
        CustomSort sorter = new CustomSort();
        List<Integer> numbers = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(expected, sorter.customSort(numbers));
    }

    @Test
    public void testCustomSortRandomOrder() {
        CustomSort sorter = new CustomSort();
        List<Integer> numbers = Arrays.asList(4, 1, 3, 5, 2);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(expected, sorter.customSort(numbers));
    }

    @Test
    public void testCustomSortEmptyList() {
        CustomSort sorter = new CustomSort();
        List<Integer> numbers = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, sorter.customSort(numbers));
    }

    @Test
    public void testCustomSortSingleElement() {
        CustomSort sorter = new CustomSort();
        List<Integer> numbers = Collections.singletonList(1);
        List<Integer> expected = Collections.singletonList(1);
        assertEquals(expected, sorter.customSort(numbers));
    }
}