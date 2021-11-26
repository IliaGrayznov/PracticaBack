package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.model.Order;
import inreco.vlgu.practic.model.Product;
import inreco.vlgu.practic.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ClientService {
    public boolean addProductToCart(OrderRequest orderRequest, User user);
    public boolean deleteProductFromCart(OrderRequest orderRequest, User user);
    public List<Product> showCart(User user);
    public boolean order(User user);
    public List<Order> showOrders(User user);
}
