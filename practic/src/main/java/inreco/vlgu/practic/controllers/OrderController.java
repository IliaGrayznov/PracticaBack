package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.dto.product.ProductResponse;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addProductOrder(@Valid @RequestBody OrderRequest orderRequest,
                                                       Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(orderService.addProductToCart(orderRequest, user))
            return ResponseEntity.ok(new MessageResponse("Продукт успешно добавлен в корзину!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> deleteProductOrder(@Valid @RequestBody OrderRequest orderRequest,
                                                       Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(orderService.deleteProductFromCart(orderRequest, user))
            return ResponseEntity.ok(new MessageResponse("Продукт удален из корзины!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @GetMapping("/confirm")
    public ResponseEntity<ProductResponse> confirmAndShowOrder(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new ProductResponse(orderService.confirmAndShowOrder(user)));
    }

    @GetMapping("/show")
    public ResponseEntity<ProductResponse> showOrder(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new ProductResponse(orderService.showOrder(user)));
    }
}
