package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select s from Product s")
    List<Product> getAllProducts();
}
