package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerImp implements HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
