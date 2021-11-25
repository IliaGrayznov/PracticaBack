package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.dto.order.OrderRequest;
import inreco.vlgu.practic.dto.order.OrderResponse;
import inreco.vlgu.practic.dto.product.ProductResponse;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.CartService;
import inreco.vlgu.practic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/show")
    public ResponseEntity<OrderResponse> showConfirmedOrder() {
        return ResponseEntity.ok(new OrderResponse(managerService.showConfirmedOrders()));
    }
}
