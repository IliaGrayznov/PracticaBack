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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ProductResponse> products() {
        return ResponseEntity.ok(new ProductResponse(productService.getProducts()));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createProduct(@Valid @RequestBody
                                                                     ProductCreateRequest productCreateRequest) {
        if(productService.createProduct(productCreateRequest))
            return ResponseEntity.ok(new MessageResponse("Продукт успешно добавлен в каталог!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Ошибка: что-то пошло не так("));
    }
}
