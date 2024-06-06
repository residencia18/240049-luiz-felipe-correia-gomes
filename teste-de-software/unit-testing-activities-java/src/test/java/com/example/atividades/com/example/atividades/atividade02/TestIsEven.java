package com.example.atividades.atividade02;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestIsEven {
    private final IsEven atividade = new IsEven();

    @Test
    public void testIsEvenWithEvenNumber() {
        assertTrue(atividade.isEven(4));
    }

    @Test
    public void testIsEvenWithOddNumber() {
        assertFalse(atividade.isEven(5));
    }

    @Test
    public void testIsEvenWithZero() {
        assertTrue(atividade.isEven(0));
    }
}
