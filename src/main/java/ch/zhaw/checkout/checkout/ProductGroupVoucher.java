package ch.zhaw.checkout.checkout;

import java.util.List;

public class ProductGroupVoucher implements Voucher {

    private String productGroup;
    private int amount;
    static String errorMessageProductBlank = "Error: Product group must not be blank.";
    static String errorMessageProductNull = "Error: Product group must not be null.";
    static String errorMessageAmount = "Error: Amount must be positive value.";

    public ProductGroupVoucher(String productGroup, int amount) {
        if (productGroup == null) {
            throw new RuntimeException(errorMessageProductNull);
        } else if (productGroup.isBlank()) {
            throw new RuntimeException(errorMessageProductBlank);
        } else if (amount <= 0) {
            throw new RuntimeException(errorMessageAmount);
        }
        this.productGroup = productGroup;
        this.amount = amount;
    }

    @Override
    public double getDiscount(List<Product> products) {
        var totalPrice = products.stream().filter(p -> this.productGroup.equals(p.getProductGroup()))
                .mapToDouble(p -> p.getPrice()).sum();
        return Math.min(totalPrice, this.amount);
    }

}
