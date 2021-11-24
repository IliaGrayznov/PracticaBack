package inreco.vlgu.practic.dto.order;

public class OrderRequest {
    private long product_id;
    private int amount;

    public OrderRequest() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}

