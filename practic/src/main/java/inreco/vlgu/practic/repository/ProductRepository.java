package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /*@Query("select p from Product p join OrderProduct op on p.id = op.product.id where op.order.id=:idO")
    List<Product> getAllProductsInOrder(@Param("idO") long idO);*/
    @Query("select p from Product p where p.amount_in_warehouse>0")
    List<Product> findAllWithoutNotExist ();
}
