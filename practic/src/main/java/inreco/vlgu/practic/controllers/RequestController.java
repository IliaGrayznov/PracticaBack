package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.MessageResponse;
import inreco.vlgu.practic.dto.request.InputRegisterRequest;
import inreco.vlgu.practic.dto.request.RequestListResponse;
import inreco.vlgu.practic.dto.request.InputRequestID;
import inreco.vlgu.practic.dto.request.ServiceListResponse;
import inreco.vlgu.practic.model.Request;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.RequestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestService requestService;

    @PostMapping("/create")
    @ApiOperation("Создает запрос на обслуживание")
    public ResponseEntity<MessageResponse> createRequest(@Valid @RequestBody InputRegisterRequest registerRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(requestService.createRequest(user,registerRequest.getCar_id(),registerRequest.getDate(),registerRequest.getIdS1(),registerRequest.getIdS2(), registerRequest.getIdS3()))
            return ResponseEntity.ok(new MessageResponse("Request registered successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @GetMapping("/userS")
    @ApiOperation("Возвращает все запросы на обслуживание, где клиент==текущей пользователь")
    public ResponseEntity<RequestListResponse> userRequests(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        List<Request> requests = user.getClientRequests();
        return ResponseEntity.ok(new RequestListResponse(requests));
    }

    @GetMapping("/mastersS")
    @ApiOperation("Возвращает все запросы на обслуживание, где мастер==текущей пользователь")
    public ResponseEntity<RequestListResponse> masterRequests(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        List<Request> requests = requestService.getMastersRequests(user.getId());
        return ResponseEntity.ok(new RequestListResponse(requests));
    }

    @GetMapping("/all")
    @ApiOperation("Возвращает все запросы на обслуживание")
    public ResponseEntity<RequestListResponse> Requests() {
        List<Request> requests = requestService.getRequests();
        return ResponseEntity.ok(new RequestListResponse(requests));
    }


    @GetMapping("/servicesIN/{id}")
    @ApiOperation("Возвращает все работы, которые необходимо провести в рамках данного запроса")
    public ResponseEntity<ServiceListResponse> servicesInRequests(@PathVariable("id") int id) {
        return ResponseEntity.ok(new ServiceListResponse(requestService.getOneRequest(id).getServices()));
    }

    @GetMapping("/requestByStatus/{id}")
    @ApiOperation("Возвращает все запросы на обслуживание с определенным статусом(id)")
    public ResponseEntity<?> acceptWaitRequests(@PathVariable("id") int id) {
        return ResponseEntity.ok(requestService.getRequestsByStatus(id));
    }

    @PostMapping("/acceptRequest")
    @ApiOperation("Принимает запрос на обслуживание(изменяет статус) и назначает ему мастера==текущему пользователю")
    public ResponseEntity<MessageResponse> acceptRequest(@Valid @RequestBody InputRequestID inputRequestID, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        String s = requestService.acceptRequest(user,inputRequestID);
        switch (s){
            case "OK":
                return ResponseEntity.ok(new MessageResponse("Request accepted successfully!"));
            case "BAD":
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Something went wrong("));
            default:
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(s));
        }
    }

    @PostMapping("/rejectRequest")
    @ApiOperation("Отклоняет запрос на обслуживание(изменяет статус) и назначает ему мастера==текущему пользователю")
    public ResponseEntity<MessageResponse> rejectRequest(@Valid @RequestBody InputRequestID inputRequestID, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        String s = requestService.rejectRequest(inputRequestID,user);
        switch (s){
            case "OK":
                return ResponseEntity.ok(new MessageResponse("Request rejected successfully!"));
            case "BAD":
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Something went wrong("));
            default:
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(s));
        }
    }

    @PostMapping("/startService")
    @ApiOperation("Изменяет статус запроса на обслуживание на Repair и возвращает все работы, которые необходимо провести в рамках данного запроса")
    public ResponseEntity<ServiceListResponse> startService(@Valid @RequestBody InputRequestID inputRequestID) {
        return ResponseEntity.ok(new ServiceListResponse(requestService.service(inputRequestID)));
    }

    @PostMapping("/finishService")
    @ApiOperation("Изменяет статус запроса на обслуживание на Repaired")
    public ResponseEntity<MessageResponse> finishService(@Valid @RequestBody InputRequestID inputRequestID) {
        String s = requestService.finishService(inputRequestID);
        switch (s){
            case "OK":
                return ResponseEntity.ok(new MessageResponse("Servicing finished successfully!"));
            case "BAD":
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Something went wrong("));
            default:
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(s));
        }
    }

    @PostMapping("/payService")
    @ApiOperation("Изменяет статус запроса на обслуживание на Finished. Также тут ДОЛЖНА БЫТЬ оплата")
    public ResponseEntity<MessageResponse> payService(@Valid @RequestBody InputRequestID inputRequestID) {
        if(requestService.payService(inputRequestID))
            return ResponseEntity.ok(new MessageResponse("Request payed successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }
}
