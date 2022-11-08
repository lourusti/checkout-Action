package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PercentageVoucherTest {

    // XXX Aufgabe 6d)
    @Test
    public void testVoucher_withoutProducts() {
        var voucher = new PercentageVoucher(7);
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()), 0.01);
    }

    // XXX Aufgabe 6d)
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49, 50 })
    public void testVoucher_withoutProducts_multipleValues(int value) {
        var voucher = new PercentageVoucher(value);
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()), 0.01);
    }

    // XXX Aufgabe 6d)
    @Test
    public void testVoucher_withProducts() {
        var voucher = new PercentageVoucher(42);
        var product1 = new Product("id1", "Dunkle Schokolade", "A", 77);
        var product2 = new Product("id2", "Weisse Schokolade", "A", 42);

        assertEquals(49.98, voucher.getDiscount(Arrays.asList(product1, product2)), 0.01);
    }

    // XXX Aufgabe 7c)
    @Test
    public void testVoucher_withProducts_Mock() {
        var voucher = new PercentageVoucher(42);

        var product1 = mock(Product.class);
        var product2 = mock(Product.class);
        when(product1.getPrice()).thenReturn(77.0);
        when(product2.getPrice()).thenReturn(42.0);

        assertEquals(49.98, voucher.getDiscount(Arrays.asList(product1, product2)), 0.01);
    }

    // XXX Aufgabe 7b)
    @Test
    public void testVoucher_belowOrEqualZero() {
        var exception1 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(0);
        });
        assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(-5);
        });
        assertEquals(PercentageVoucher.errorMessageGreaterZero, exception1.getMessage());
    }

    // XXX Aufgabe 7b)
    @Test
    public void testVoucher_greater50() {
        var exception1 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(51);
        });
        assertEquals(PercentageVoucher.errorMessage50, exception1.getMessage());

        var exception2 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(120);
        });
        assertEquals(PercentageVoucher.errorMessage50, exception2.getMessage());
    }

}
