package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.dto.order.OrderRequest;
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
    public ResponseEntity<MessageResponse> changeOrder(@Valid @RequestBody OrderRequest orderRequest,
                                                       Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(orderService.addProductToCart(orderRequest, user))
            return ResponseEntity.ok(new MessageResponse("Продукт успешно добавлен в корзину!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }
}
