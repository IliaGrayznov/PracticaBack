package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.OrderProduct;
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
    public boolean addProductToCart(OrderRequest orderRequest, User user)  {
        // Создаем заказ с статусе "cart" и запись об этом в таблице "order", если у пользователя не создана корзина.
        Order o = orderRepository.getOrderCart(user.getId());
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

        // В op попробуем найти, есть ли уже в заказе такая позиция.
        OrderProduct op = orderProductRepository.getOneProductInOrder(o.getId(), orderRequest.getProduct_id());
        // В заказ добавляем товар. Если такой товар уже есть, то добавляем +1.
        if (op!=null) {
            op.setAmount_in_order(op.getAmount_in_order()+1);
        } else {
            op = new OrderProduct();
            op.setOrder(o);
            op.setProduct(productRepository.getById(orderRequest.getProduct_id())
            );
            op.setAmount_in_order(1);
        }
        try{
            orderProductRepository.save(op);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteProductFromCart(OrderRequest orderRequest, User user)  {
        //Если этот товар последний в корзине и его количество 1 штука - удалить заказ (сначала товар, потом заказ).
        //Если этого товара в корзине больше 1 штуки, уменьшить кол-во. Иначе удалить товар из корзины.
        Order o = orderRepository.getOrderCart(user.getId());
        OrderProduct op = orderProductRepository.getOneProductInOrder(o.getId(), orderRequest.getProduct_id());
        List<OrderProduct> opList = orderProductRepository.findAllByOrderId(o.getId());
        int amountOneProduct = op.getAmount_in_order();
        if (opList.size()==1 && amountOneProduct==1) {
            try {
                orderProductRepository.delete(op);
                orderRepository.delete(o);
            } catch (Exception e) {
                return false;
            }
        }
        if (amountOneProduct > 1) {
            op.setAmount_in_order(op.getAmount_in_order()-1);
        } else {
            try {
                orderProductRepository.delete(op);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
