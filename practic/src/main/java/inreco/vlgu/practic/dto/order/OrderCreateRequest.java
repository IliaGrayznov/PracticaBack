package inreco.vlgu.practic.dto.order;

public class OrderCreateRequest {
    private long product_id;

    public OrderCreateRequest() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}

