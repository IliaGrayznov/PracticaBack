package inreco.vlgu.practic.dto.product;

public class ProductChangeRequest {
    private long product_id;
    private String name;
    private String description;
    private String img;
    private Integer price;
    private Integer amount_in_warehouse;
    private long category_id;

    public ProductChangeRequest() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount_in_warehouse() {
        return amount_in_warehouse;
    }

    public void setAmount_in_warehouse(Integer amount_in_warehouse) {
        this.amount_in_warehouse = amount_in_warehouse;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}

