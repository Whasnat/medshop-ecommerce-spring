package com.medshop.admin.user;


import com.medshop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public String LisAll(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }


    @GetMapping("/users/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        System.out.println(user);
        return "redirect:/users";
    }
}
