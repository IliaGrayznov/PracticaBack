package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.OrderStatus;
import inreco.vlgu.practic.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}
