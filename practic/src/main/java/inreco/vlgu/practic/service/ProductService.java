package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.product.ProductChangeRequest;
import inreco.vlgu.practic.dto.product.ProductCreateRequest;
import inreco.vlgu.practic.dto.product.ProductDeleteRequest;
import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.model.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService  {
    public List<Product> getProducts();
    public List<Product> getProductsManager();
    public boolean createProduct(ProductCreateRequest productCreateRequest);
    public boolean changeProduct(ProductChangeRequest productChangeRequest);
    public boolean deleteProduct(ProductDeleteRequest productDeleteRequest);
    public List<ProductCategory> getCategories();
}
