package inreco.vlgu.practic.controllers;



import inreco.vlgu.practic.dto.MessageResponse;
import inreco.vlgu.practic.dto.car.RegisterCarRequest;

import inreco.vlgu.practic.dto.car.CarResponse;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.UserRepository;
import inreco.vlgu.practic.service.CarService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("регистрирует машину клиента в системе")
    public ResponseEntity<MessageResponse> createCar(@Valid @RequestBody RegisterCarRequest registerCarRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if(carService.createCar(registerCarRequest, user))
            return ResponseEntity.ok(new MessageResponse("Car registered successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @GetMapping("/allCars")
    @ApiOperation("Возвращает список всех зарегистрированных в системе машин")
    public ResponseEntity<CarResponse> allCars() {
        return ResponseEntity.ok(new CarResponse(carService.getCars()));
    }

    @GetMapping("/cars")
    @ApiOperation("Возвращает все машины клиента")
    public ResponseEntity<CarResponse> userCars(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(new CarResponse(user.getCars()));
    }

}
