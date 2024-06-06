package com.example.atividades.atividade06;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    public void testDistanceTo() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(3, 4);
        double expectedDistance = 5.0;
        assertEquals(expectedDistance, point1.distanceTo(point2));
    }

    @Test
    public void testDistanceToNull() {
        Point point = new Point(0, 0);
        assertThrows(IllegalArgumentException.class, () -> point.distanceTo(null));
    }
}