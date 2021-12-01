package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query("select op from OrderProduct op where op.order.id=:idO and op.product.id =:idP")
    OrderProduct getOneProductInOrder(@Param("idO") long idO, @Param("idP") long idP);
    @Query("select op from OrderProduct op where op.order.id=:idO order by op.id")
    List<OrderProduct> getProductsInOrder(@Param("idO") long idO);
    @Query()
    List<OrderProduct> findAllByOrderId(@Param("idO") long idO);
}
