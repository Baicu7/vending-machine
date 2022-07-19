package vendingmachine.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping
    public String registerLogin() {
        return "Inregistrare sau login";
    }

    @GetMapping
    public String getUser() {
        return "GET - user";
    }

    @PutMapping
    public String editUser() {
        return "Modificare user";
    }

    @DeleteMapping
    public String removeUser() {
        return "Stergere user";
    }
}
