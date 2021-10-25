package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.CarResponse;
import inreco.vlgu.practic.dto.InputRequest;
import inreco.vlgu.practic.dto.RequestResponse;
import inreco.vlgu.practic.dto.ServiceResponse;
import inreco.vlgu.practic.model.Request;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.CarRepository;
import inreco.vlgu.practic.repository.RequestRepository;
import inreco.vlgu.practic.repository.ServiceListRepository;
import inreco.vlgu.practic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ServiceListRepository serviceListRepository;

    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/client")
   // @PreAuthorize("hasRole('client') or hasRole('master') or hasRole('admin')")
    public String userAccess() {
        return "Client Content.";
    }

    @GetMapping("/master")
   // @PreAuthorize("hasRole('master')")
    public String masterAccess() {
        return "Master Content.";
    }

    @GetMapping("/admin")
   // @PreAuthorize("hasRole('admin')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/cars")
    public ResponseEntity<?> userCars(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new CarResponse(user.getCars()));
    }

    @GetMapping("/allCars")
    public ResponseEntity<?> allCars() {
        return ResponseEntity.ok(new CarResponse(carRepository.findAll()));
    }

    @GetMapping("/services")
    public ResponseEntity<?> services() {
        return ResponseEntity.ok(new ServiceResponse(serviceListRepository.findAll()));
    }

    @GetMapping("/requests")
    public ResponseEntity<?> userRequests(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new RequestResponse(user.getClientRequests()));
    }

    @GetMapping("/allRequests")
    public ResponseEntity<?> Requests() {
        return ResponseEntity.ok(new RequestResponse(requestRepository.findAll()));
    }

    @GetMapping("/request/services") //   /{id}/services"
    public ResponseEntity<?> servicesInRequests(@Valid @RequestBody InputRequest inputRequest) {
        Request request = requestRepository.getById(inputRequest.getId());
        return ResponseEntity.ok(new ServiceResponse(request.getServices()));
    }
}
