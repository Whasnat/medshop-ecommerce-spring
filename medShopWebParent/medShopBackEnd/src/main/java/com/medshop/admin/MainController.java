package com.medshop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("")
    public String viewHomePage() {
        String index = "index";
        return index;
    }

    @GetMapping("/login")
    public String viewLoginpage() {
        return "login";
    }
}
