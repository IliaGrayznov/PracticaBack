package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.request.ServiceListResponse;
import inreco.vlgu.practic.repository.CarRepository;
import inreco.vlgu.practic.repository.RequestRepository;
import inreco.vlgu.practic.repository.ServiceListRepository;
import inreco.vlgu.practic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


}
