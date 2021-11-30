package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.dto.order.OrderResponse;
import inreco.vlgu.practic.dto.product.ProductCategoryResponse;
import inreco.vlgu.practic.dto.product.ProductResponse;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.ClientService;
import inreco.vlgu.practic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/order/add")
    public ResponseEntity<MessageResponse> addProduct(@Valid @RequestBody OrderRequest orderRequest,
                                                       Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(clientService.addProductToCart(orderRequest, user))
            return ResponseEntity.ok(new MessageResponse("Продукт успешно добавлен в корзину!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @PostMapping("/order/delete")
    public ResponseEntity<MessageResponse> deleteProduct(@Valid @RequestBody OrderRequest orderRequest,
                                                       Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(clientService.deleteProductFromCart(orderRequest, user))
            return ResponseEntity.ok(new MessageResponse("Продукт удален из корзины!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @GetMapping("/order/confirm")
    public ResponseEntity<MessageResponse> confirm(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(clientService.order(user))
            return ResponseEntity.ok(new MessageResponse("Ваш заказ оформлен!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @GetMapping("/order/show")
    public ResponseEntity<ProductResponse> showCart(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new ProductResponse(clientService.showCart(user)));
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderResponse> showOrders(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new OrderResponse(clientService.showOrders(user)));
    }

    @GetMapping("/categories")
    public ResponseEntity<ProductCategoryResponse> showCategories() {
        return ResponseEntity.ok(new ProductCategoryResponse(productService.getCategories()));
    }
}
