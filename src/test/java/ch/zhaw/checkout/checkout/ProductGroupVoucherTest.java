package ch.zhaw.checkout.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductGroupVoucherTest {

    // XXX Aufgabe 7e)
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49, Integer.MAX_VALUE })
    public void testAmountParameter(int amount) {
        var voucher = new ProductGroupVoucher("a", amount);
        assertEquals(0, voucher.getDiscount(new ArrayList<Product>()));
    }

    // XXX Aufgabe 7e)
    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -Integer.MAX_VALUE })
    public void testAmountParameter_Error(int amount) {
        var exception = assertThrows(RuntimeException.class, () -> {
            new ProductGroupVoucher("a", amount);
        });
        assertEquals(ProductGroupVoucher.errorMessageAmount, exception.getMessage());
    }

    // XXX Aufgabe 7f)
    @ParameterizedTest
    @CsvSource({ "p1,5,p1,7,p1,10,10", "p1,5,p1,3,p1,10,8", "p1,5,p2,7,p1,10,5" })
    void testProductGroupExample(ArgumentsAccessor argumentsAccessor) {
        var product1 = mock(Product.class);
        var product2 = mock(Product.class);
        when(product1.getProductGroup()).thenReturn(argumentsAccessor.getString(0));
        when(product1.getPrice()).thenReturn(argumentsAccessor.getDouble(1));
        when(product2.getProductGroup()).thenReturn(argumentsAccessor.getString(2));
        when(product2.getPrice()).thenReturn(argumentsAccessor.getDouble(3));
        var products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);

        var voucher = new ProductGroupVoucher(argumentsAccessor.getString(4), argumentsAccessor.getInteger(5));
        var discount = voucher.getDiscount(products);
        assertEquals(argumentsAccessor.getDouble(6), discount);
    }

}
