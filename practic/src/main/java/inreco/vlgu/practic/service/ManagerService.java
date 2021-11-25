package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.OrderProduct;
import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ManagerService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<Order> showConfirmedOrders()  {
        List<Order> oList = orderRepository.getConfirmedOrder();
        return oList;
    }
}
