package inreco.vlgu.practic.model;

import javax.persistence.*;

@Entity
@Table(name = "order_product",schema = "public")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    Order order;

    @ManyToOne
    Product product;

    @Column(name = "amount_in_order")
    private int amount_in_order;

    public OrderProduct() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount_in_order() {
        return amount_in_order;
    }

    public void setAmount_in_order(int amount_in_order) {
        this.amount_in_order = amount_in_order;
    }
}

