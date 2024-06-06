package com.example.atividades.atividade08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class StatisticsTest {

    @Test
    public void testCalculateAveragePositiveNumbers() {
        Statistics stats = new Statistics();
        double expectedAverage = 3.0;
        assertEquals(expectedAverage, stats.calculateAverage(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testCalculateAverageNegativeNumbers() {
        Statistics stats = new Statistics();
        double expectedAverage = -3.0;
        assertEquals(expectedAverage, stats.calculateAverage(Arrays.asList(-1, -2, -3, -4, -5)));
    }

    @Test
    public void testCalculateAverageMixedNumbers() {
        Statistics stats = new Statistics();
        double expectedAverage = 0.0;
        assertEquals(expectedAverage, stats.calculateAverage(Arrays.asList(-2, -1, 0, 1, 2)));
    }

    @Test
    public void testCalculateAverageWithZero() {
        Statistics stats = new Statistics();
        double expectedAverage = 0.0;
        assertEquals(expectedAverage, stats.calculateAverage(Collections.singletonList(0)));
    }

    @Test
    public void testCalculateAverageEmptyList() {
        Statistics stats = new Statistics();
        assertThrows(IllegalArgumentException.class, () -> stats.calculateAverage(Collections.emptyList()));
    }

    @Test
    public void testCalculateAverageNullList() {
        Statistics stats = new Statistics();
        assertThrows(IllegalArgumentException.class, () -> stats.calculateAverage(null));
    }
}