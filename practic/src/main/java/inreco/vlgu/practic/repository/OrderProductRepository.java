package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query("select s from Product s")
    List<OrderProduct> getProductsInOrder();
}
