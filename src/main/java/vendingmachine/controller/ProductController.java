package vendingmachine.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    public String getProduct() {
        return "Lista produse";
    }

    @PostMapping
    public String createProduct() {
        return "Adaugare produs";
    }

    @PutMapping
    public String editProduct() {
        return "Modificare produs";
    }

    @DeleteMapping
    public String removeProduct() {
        return "Stergere produs";
    }
}
