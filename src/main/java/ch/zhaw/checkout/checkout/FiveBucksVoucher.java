package ch.zhaw.checkout.checkout;

import java.util.List;

public class FiveBucksVoucher implements Voucher {

    @Override
    public double getDiscount(List<Product> products) {
        var sum = products.stream().mapToDouble(p -> p.getPrice()).sum();
        if (sum >= 10) {
            return 5;
        }
        return 0;
    }

}
