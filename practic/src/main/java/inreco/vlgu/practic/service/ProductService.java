package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.product.ProductCreateRequest;
import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.repository.ProductCategoryRepository;
import inreco.vlgu.practic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService  {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<Product> getProducts()  {
        return productRepository.findAll();
    }

    @Transactional
    public boolean createProduct(ProductCreateRequest productCreateRequest)  {
        Product p = new Product();
        p.setName(productCreateRequest.getName());
        p.setDescription(productCreateRequest.getDescription());
        p.setImg(productCreateRequest.getImg());
        p.setPrice(productCreateRequest.getPrice());
        p.setAmount_in_warehouse(productCreateRequest.getAmount_in_warehouse());
        p.setProductCategory(
                productCategoryRepository.getById(
                        productCreateRequest.getCategory_id()
                )
        );
        try{
            productRepository.save(p);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

}
