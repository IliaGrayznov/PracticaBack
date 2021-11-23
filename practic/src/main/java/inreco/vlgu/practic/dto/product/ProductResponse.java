package inreco.vlgu.practic.dto.product;

import inreco.vlgu.practic.model.Product;

import java.util.List;

public class ProductResponse {
    private List<Product> products;

    public ProductResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> cars) {
        this.products = cars;
    }
}
