package inreco.vlgu.practic.model;

import javax.persistence.*;

@Entity
@Table(name = "product",schema = "public")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "img")
    private String img;

    @Column(name = "price")
    private int price;

    @Column(name = "amount_in_warehouse")
    private int amount_in_warehouse;

    @ManyToOne
    @JoinColumn(name = "category_id")
    ProductCategory productCategory;

    public Product() {
    }

    public Product(long id, String name, String description, String img, int price, int amount_in_warehouse,
                   ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;
        this.amount_in_warehouse = amount_in_warehouse;
        this.productCategory = productCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount_in_warehouse() {
        return amount_in_warehouse;
    }

    public void setAmount_in_warehouse(int amount_in_warehouse) {
        this.amount_in_warehouse = amount_in_warehouse;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}

