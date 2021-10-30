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
    public ResponseEntity<?> createRequest(@Valid @RequestBody InputRegisterRequest registerRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(requestService.createRequest(user,registerRequest.getCar_id(),registerRequest.getDate(),registerRequest.getServices()))
            return ResponseEntity.ok(new MessageResponse("Request registered successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @GetMapping("/userS")
    public ResponseEntity<?> userRequests(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        List<Request> requests = user.getClientRequests();
        return ResponseEntity.ok(new RequestListResponse(requests));
    }

    @GetMapping("/mastersS")
    public ResponseEntity<?> masterRequests(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        List<Request> requests = user.getMasterRequests();
        return ResponseEntity.ok(new RequestListResponse(requests));
    }

    @GetMapping("/all")
    public ResponseEntity<?> Requests() {
        List<Request> requests = requestService.getRequests();
        return ResponseEntity.ok(new RequestListResponse(requests));
    }


    @GetMapping("/servicesIN") //   /{id}/services"
    public ResponseEntity<?> servicesInRequests(@Valid @RequestBody InputRequestID inputRequestID) {
        return ResponseEntity.ok(new ServiceListResponse(requestService.getOneRequest(inputRequestID.getId()).getServices()));
    }

    @GetMapping("/aссeptWait")
    public ResponseEntity<?> acceptWaitRequests() {
        return ResponseEntity.ok(requestService.getRequestsByStatus(2));
    }

    @PostMapping("/acceptRequest")
    public ResponseEntity<?> acceptRequest(@Valid @RequestBody InputRequestID inputRequestID, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(requestService.acceptRequest(user,inputRequestID.getId()))
            return ResponseEntity.ok(new MessageResponse("Request accepted successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/rejectRequest")
    public ResponseEntity<?> rejectRequest(@Valid @RequestBody InputRequestID inputRequestID, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        String s = requestService.rejectRequest(inputRequestID.getId(),user);
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

    @PostMapping("/startService")
    public ResponseEntity<?> startService(@Valid @RequestBody InputRequestID inputRequestID) {
        return ResponseEntity.ok(new ServiceListResponse(requestService.service(inputRequestID.getId())));
    }

    @PostMapping("/finishService")
    public ResponseEntity<?> finishService(@Valid @RequestBody InputRequestID inputRequestID) {
        if(requestService.finishService(inputRequestID.getId()))
            return ResponseEntity.ok(new MessageResponse("Request finished successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }
}
