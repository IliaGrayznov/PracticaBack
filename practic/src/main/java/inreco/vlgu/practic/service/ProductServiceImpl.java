package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.product.ProductChangeRequest;
import inreco.vlgu.practic.dto.product.ProductCreateRequest;
import inreco.vlgu.practic.dto.product.ProductDeleteRequest;
import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.model.ProductCategory;
import inreco.vlgu.practic.repository.ProductCategoryRepository;
import inreco.vlgu.practic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<Product> getProducts()  {
        return productRepository.findAllWithoutNotExist();
    }

    public List<ProductCategory> getCategories()  {
        return productCategoryRepository.findAll();
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

    @Transactional
    public boolean changeProduct(ProductChangeRequest productChangeRequest)  {
        Product p = productRepository.getById(productChangeRequest.getProduct_id());
        p.setName(productChangeRequest.getName());
        p.setDescription(productChangeRequest.getDescription());
        p.setImg(productChangeRequest.getImg());
        p.setPrice(productChangeRequest.getPrice());
        p.setAmount_in_warehouse(productChangeRequest.getAmount_in_warehouse());
        p.setProductCategory(
                productCategoryRepository.getById(
                        productChangeRequest.getCategory_id()
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

    @Transactional
    public boolean deleteProduct(ProductDeleteRequest productDeleteRequest)  {
        //Получить товар. Удалить его.
        Product p = productRepository.getById(productDeleteRequest.getProduct_id());
        try {
            productRepository.delete(p);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
