package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.id=:id and o.orderStatus.id=1")
    Order getUserOrderInStatusCart(@Param("id") long id);
    @Query("select o from Order o where o.orderStatus.id=2")
    List<Order> getOrdersInStatusOrdered();
    @Query("select o from Order o where o.orderStatus.id=3")
    List<Order> getOrdersInStatusConfirmed();
    @Query()
    List<Order> findAllByUserId(@Param("id") long id);
    @Query()
    Order findById(@Param("id") long id);
}
