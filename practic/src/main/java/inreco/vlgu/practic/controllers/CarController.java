package inreco.vlgu.practic.controllers;



import inreco.vlgu.practic.dto.MessageResponse;
import inreco.vlgu.practic.dto.car.RegisterCarRequest;

import inreco.vlgu.practic.dto.car.CarResponse;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<?> createCar(@Valid @RequestBody RegisterCarRequest registerCarRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(carService.createCar(registerCarRequest.getNumber(), registerCarRequest.getMark(), registerCarRequest.getModel(),user))
            return ResponseEntity.ok(new MessageResponse("Car registered successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @GetMapping("/allCars")
    public ResponseEntity<?> allCars() {
        return ResponseEntity.ok(new CarResponse(carService.getCars()));
    }

    @GetMapping("/cars")
    public ResponseEntity<?> userCars(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new CarResponse(user.getCars()));
    }

}
