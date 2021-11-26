package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.OrderStatusRequest;
import inreco.vlgu.practic.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ManagerService {
    public List<Order> showOrdersInStatusOrdered();
    public List<Order> showOrdersInStatusConfirmed();
    public boolean deleteOrder(OrderStatusRequest orderStatusRequest);
    public boolean confirm(OrderStatusRequest orderStatusRequest);
    public boolean close(OrderStatusRequest orderStatusRequest);
}
