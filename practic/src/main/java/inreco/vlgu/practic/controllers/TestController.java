package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.car.CarResponse;
import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.CarRepository;
import inreco.vlgu.practic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

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
    public String moderatorAccess() {
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
        return ResponseEntity.ok(new CarResponse(carRepository.getUserCars(user.getId())));
    }

    @GetMapping("/allCars")
    public ResponseEntity<?> allCars() {
        return ResponseEntity.ok(new CarResponse(carRepository.findAll()));
    }
}
