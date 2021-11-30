package inreco.vlgu.practic.dto.order;

import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.Product;

import java.util.List;

public class CartResponse {
    private Product product;
    private int amount;

    public CartResponse() {
    }

    public CartResponse(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
