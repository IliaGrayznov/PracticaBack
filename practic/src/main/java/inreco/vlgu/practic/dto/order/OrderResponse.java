package inreco.vlgu.practic.dto.order;

import inreco.vlgu.practic.model.Order;

import java.util.List;

public class OrderResponse {
    private List<Order> orders;

    public OrderResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
