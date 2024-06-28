package exercise;

import org.junit.jupiter.api.Test;

import static exercise.PaymentMetrics.MetricType.*;
import static exercise.PaymentMetrics.createMetrics;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentMetricsTest {

    /*****************************************\
     Tests for 10000 window
     \*****************************************/
    @Test
    public void shouldCalculateTotal50() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(TOTAL);
        // When
        var result = paymentMetrics.post(1000L, 50L);
        // Then
        assertEquals(50L, result);
    }
    @Test
    public void shouldHandleLong() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(TOTAL);
        // When
        long result = paymentMetrics.post(1000L, (long)Integer.MAX_VALUE + 1);
        // Then
        assertEquals((long)Integer.MAX_VALUE + 1, result);
    }
    @Test
    public void shouldCalculateTotal150() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(TOTAL);
        // When
        paymentMetrics.post(1000L, 50L);
        var result = paymentMetrics.post(2000L, 100L);
        // Then
        assertEquals(150L, result);

    }
    @Test
    public void shouldCalculateTotalTotal130() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(TOTAL);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        // When
        var result = paymentMetrics.post(11000L, 20L);
        // Then
        assertEquals(130L, result);
    }
    @Test
    public void shouldCalculateTotal100() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(TOTAL);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        paymentMetrics.post(11000L, 20L);
        // When
        var result = paymentMetrics.post(30000L, 100L);
        // Then
        assertEquals(100L, result);

    }
    @Test
    public void shouldCount1() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(COUNT);
        // When
        var result = paymentMetrics.post(1000L, 50L);
        // Then
        assertEquals(1L, result);
    }
    @Test
    public void shouldCount3() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(COUNT);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        // When
        var result = paymentMetrics.post(11000L, 20L);
        // Then
        assertEquals(3L, result);
    }
    @Test
    public void shouldAverage63() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(AVERAGE);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        // When
        var result = paymentMetrics.post(11000L, 20L);
        // Then
        assertEquals(43L, result);
    }

    /*****************************************\
     Tests for 20000 window
    \*****************************************/
    @Test
    public void shouldCalculateTotal120for20000Window() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(TOTAL, 20000);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        paymentMetrics.post(11000L, 20L);
        // When
        var result = paymentMetrics.post(30000L, 100L);
        // Then
        assertEquals(120L, result);
    }
    @Test
    public void shouldCount3for20000Window() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(COUNT, 20000);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        paymentMetrics.post(11000L, 20L);
        // When
        var result = paymentMetrics.post(30000L, 100L);
        // Then
        assertEquals(2L, result);
    }
    @Test
    public void shouldAverageFor20000Window() {
        // Given
        PaymentMetrics paymentMetrics = createMetrics(AVERAGE, 20000);
        paymentMetrics.post(1000L, 50L);
        paymentMetrics.post(2000L, 100L);
        paymentMetrics.post(4000L, 10L);
        paymentMetrics.post(11000L, 20L);
        // When
        var result = paymentMetrics.post(30000L, 100L);
        // Then
        assertEquals(60L, result);
    }
}
