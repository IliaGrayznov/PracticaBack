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
    private int amount_in_warehouse;
}

