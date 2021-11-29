package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/client")
    public String userAccess() {
        return "Client Content.";
    }

    @GetMapping("/master")
    public String masterAccess() {
        return "Master Content.";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/manager")
    public String managerAccess() {
        return "Manager Board.";
    }
}
