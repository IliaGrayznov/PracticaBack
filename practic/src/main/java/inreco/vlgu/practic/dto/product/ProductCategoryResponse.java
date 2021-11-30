package inreco.vlgu.practic.dto.product;

import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.model.ProductCategory;

import java.util.List;

public class ProductCategoryResponse {
    private List<ProductCategory> categories;

    public ProductCategoryResponse(List<ProductCategory> categories) {
        this.categories = categories;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }
}
