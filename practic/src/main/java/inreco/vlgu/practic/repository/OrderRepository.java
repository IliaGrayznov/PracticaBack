package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select s from Order s")
    List<Order> getAllOrders();
}
