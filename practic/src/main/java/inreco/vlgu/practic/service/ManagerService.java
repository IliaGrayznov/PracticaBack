package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.OrderStatusRequest;
import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.OrderProduct;
import inreco.vlgu.practic.model.Product;
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
    public List<Order> showOrdersInStatusOrdered()  {
        List<Order> oList = orderRepository.getOrdersInStatusOrdered();
        return oList;
    }

    @Transactional
    public List<Order> showOrdersInStatusConfirmed()  {
        List<Order> oList = orderRepository.getOrdersInStatusConfirmed();
        return oList;
    }

    @Transactional
    public boolean deleteOrder(OrderStatusRequest orderStatusRequest)  {
        //Получить заказ.
        //Получить все товары в заказе.
        //Если заказ не в статусе "корзина", то вернуть товары на склад.
        //Удалить удалить все товары из заказа.
        //Удалить заказ.
        Order o = orderRepository.findById(orderStatusRequest.getOrder_id());
        if (o!=null) {
            List<OrderProduct> opList = orderProductRepository.findAllByOrderId(o.getId());
            for (int i = 0; i < opList.size(); i++) {
                OrderProduct op = opList.get(i);
                if (o.getOrderStatus().getId()!=1) {
                    Product p = op.getProduct();
                    int amountInOrder = op.getAmount_in_order();
                    int amountInWarehouse = p.getAmount_in_warehouse();
                    p.setAmount_in_warehouse(amountInWarehouse+amountInOrder);
                    try {
                        productRepository.save(p);
                    } catch (Exception e) {
                        return false;
                    }
                }
                try {
                    orderProductRepository.delete(op);
                } catch (Exception e) {
                    return false;
                }
            }
            try {
                orderRepository.delete(o);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean confirm(OrderStatusRequest orderStatusRequest)  {
        //Получить заказ пользователя. Поменять статус на "оформлен".
        Order o = orderRepository.findById(orderStatusRequest.getOrder_id());
        o.setOrderStatus(orderStatusRepository.getById((long)3));
        try {
            orderRepository.save(o);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean close(OrderStatusRequest orderStatusRequest)  {
        //Получить заказ пользователя. Поменять статус на "закрыт(исполнен)".
        Order o = orderRepository.findById(orderStatusRequest.getOrder_id());
        o.setOrderStatus(orderStatusRepository.getById((long)4));
        try {
            orderRepository.save(o);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
