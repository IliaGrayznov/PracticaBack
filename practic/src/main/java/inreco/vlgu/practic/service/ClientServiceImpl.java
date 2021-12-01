package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.AmountCartResponse;
import inreco.vlgu.practic.dto.order.CartResponse;
import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.OrderProduct;
import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {
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
        Order o = orderRepository.getUserOrderInStatusCart(user.getId());
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
        Order o = orderRepository.getUserOrderInStatusCart(user.getId());
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

    @Transactional
    public List<CartResponse> showCart(User user)  {
        Order o = orderRepository.getUserOrderInStatusCart(user.getId());
        List<CartResponse> cartList = new ArrayList<>();
        try {
            List<OrderProduct> opList = orderProductRepository.getProductsInOrder(o.getId());
            for (OrderProduct op :
                    opList) {
                CartResponse c = new CartResponse();
                c.setProduct(op.getProduct());
                c.setAmount(op.getAmount_in_order());
                cartList.add(c);
            }
        } catch (Exception e) {
            return cartList;
        }
        return cartList;
    }

    @Transactional
    public AmountCartResponse showAmountProductsInCart(User user)  {
        Order o = orderRepository.getUserOrderInStatusCart(user.getId());
        int amount = 0;
        AmountCartResponse acr = new AmountCartResponse();
        try {
            List<OrderProduct> opList = orderProductRepository.getProductsInOrder(o.getId());
            for (OrderProduct op :
                    opList) {
                amount++;
            }
        } catch (Exception e) {
            acr.setAmount(amount);
            return acr;
        }
        acr.setAmount(amount);
        return acr;
    }

    @Transactional
    public boolean order(User user)  {
        //Проверяем, есть ли указанные позиции в нужном количестве на складе.
        //Берем каждый товар в корзине, его количество в заказе и проверяем, меньше или равен он количеству на складе.
        //Если меньше или равен, то уменьшаем количество товаров на складе.
        //Статус заказа меняется.
        Order o = orderRepository.getUserOrderInStatusCart(user.getId());
        List<OrderProduct> opList = orderProductRepository.findAllByOrderId(o.getId());
        for (int i = 0; i < opList.size(); i++) {
            OrderProduct op = opList.get(i);
            Product p = op.getProduct();
            int amountInOrder = op.getAmount_in_order();
            int amountInWarehouse = p.getAmount_in_warehouse();
            if (amountInOrder <= amountInWarehouse) {
                p.setAmount_in_warehouse(amountInWarehouse-amountInOrder);
                try {
                    productRepository.save(p);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
        o.setOrderStatus(orderStatusRepository.getById((long)2));
        try {
            orderRepository.save(o);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public List<Order> showOrders(User user)  {
        //Получаем список всех заказов пользователя
        List<Order> oList = orderRepository.findAllByUserId(user.getId());
        return oList;
    }
}
