package vendingmachine.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class BuyerController {

    @PostMapping("/buy")
    public String buy() {
        return "Cumparare";
    }

    @PostMapping("/deposit")
    public String deposit() {
        return "Depozitare";
    }

    @PostMapping("/reset")
    public String reset() {
        return "Reset depozit";
    }
}
