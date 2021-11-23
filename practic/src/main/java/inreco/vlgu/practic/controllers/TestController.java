package inreco.vlgu.practic.controllers;


import inreco.vlgu.practic.dto.auth.response.MessageResponse;
import inreco.vlgu.practic.dto.product.ProductCreateRequest;
import inreco.vlgu.practic.dto.product.ProductResponse;
import inreco.vlgu.practic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/manager")
    // @PreAuthorize("hasRole('manager')")
    public String managerAccess() {
        return "Manager Board.";
    }
}
