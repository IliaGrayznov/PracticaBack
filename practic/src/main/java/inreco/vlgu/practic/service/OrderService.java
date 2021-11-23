package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.OrderCreateRequest;
import inreco.vlgu.practic.dto.product.ProductCreateRequest;
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
public class OrderService {
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
    public boolean createOrder(OrderCreateRequest orderCreateRequest, User user)  {
        // Создаем заказ с статусе "cart" и запись об этом в таблице "order", если у пользователя не создана корзина.
        Order o = orderRepository.getUserCartOrders(user.getId());
        if (o==null) {
            o = new Order();
            o.setUser(user);
            o.setOrderStatus(
                    orderStatusRepository.getById((long)1)
            );
            try{
                orderRepository.save(o);
            }
            catch (Exception e){
                return false;
            }
        }

        // В заказ добавляем товар, с помощью которого создали заказ
        OrderProduct op = new OrderProduct();
        op.setOrder(o);
        op.setProduct(
                productRepository.getById(
                        orderCreateRequest.getProduct_id()
                )
        );
        op.setAmount_in_order(1);
        try{
            orderProductRepository.save(op);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
