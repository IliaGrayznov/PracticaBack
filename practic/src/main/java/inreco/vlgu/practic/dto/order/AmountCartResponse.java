package inreco.vlgu.practic.dto.order;

import inreco.vlgu.practic.model.Product;

public class AmountCartResponse {
    private int amount;

    public AmountCartResponse() {
    }

    public AmountCartResponse(Product product, int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
