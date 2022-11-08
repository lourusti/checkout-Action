package ch.zhaw.checkout.checkout;

import java.util.List;

public class TwoForOneVoucher implements Voucher {

    Product product;

    public TwoForOneVoucher(Product product) {
        this.product = product;
    }

    @Override
    public double getDiscount(List<Product> products) {
        var count = products.stream().filter(p -> this.product.getId().equals(p.getId())).toList().size();
        return Math.floor(count / 2) * this.product.getPrice();
    }

}
