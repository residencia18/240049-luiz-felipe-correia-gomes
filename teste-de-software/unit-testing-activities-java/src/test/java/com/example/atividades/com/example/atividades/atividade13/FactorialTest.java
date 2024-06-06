package com.example.atividades.atividade13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    public void testCalculateFactorialZero() {
        Factorial factorial = new Factorial();
        assertEquals(1, factorial.calculate(0));
    }

    @Test
    public void testCalculateFactorialPositive() {
        Factorial factorial = new Factorial();
        assertEquals(120, factorial.calculate(5));
    }

    @Test
    public void testCalculateFactorialNegative() {
        Factorial factorial = new Factorial();
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-1));
    }
}