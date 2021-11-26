package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.dto.order.OrderResponse;
import inreco.vlgu.practic.dto.order.OrderStatusRequest;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/show/ordered")
    public ResponseEntity<OrderResponse> showOrderInStatusOrdered() {
        return ResponseEntity.ok(new OrderResponse(managerService.showOrdersInStatusOrdered()));
    }

    @GetMapping("/show/confirmed")
    public ResponseEntity<OrderResponse> showOrderInStatusConfirmed() {
        return ResponseEntity.ok(new OrderResponse(managerService.showOrdersInStatusConfirmed()));
    }

    @PostMapping("/order/delete")
    public ResponseEntity<MessageResponse> deleteOrder(@Valid @RequestBody OrderStatusRequest orderStatusRequest) {
        if(managerService.deleteOrder(orderStatusRequest))
            return ResponseEntity.ok(new MessageResponse("Заказ удален!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @PostMapping("/order/confirm")
    public ResponseEntity<MessageResponse> confirmOrder(@Valid @RequestBody OrderStatusRequest orderStatusRequest) {
        if(managerService.confirm(orderStatusRequest))
            return ResponseEntity.ok(new MessageResponse("Заказ подтвержден!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }

    @PostMapping("/order/close")
    public ResponseEntity<MessageResponse> closeOrder(@Valid @RequestBody OrderStatusRequest orderStatusRequest) {
        if(managerService.close(orderStatusRequest))
            return ResponseEntity.ok(new MessageResponse("Заказ закрыт!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }
}
