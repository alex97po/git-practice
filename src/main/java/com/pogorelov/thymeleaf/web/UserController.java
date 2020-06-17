package com.pogorelov.thymeleaf.web;

import com.pogorelov.thymeleaf.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.pogorelov.thymeleaf.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        User user1 = new User();
        user1.setEmail("test");
        user1.setName("test");
        userService.saveUser(user1);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

//    @PostMapping("/adduser")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//        userService.saveUser(user);
//        model.addAttribute("users", userService.getAllUsers());
//        return "index";
//    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid User user,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }


}
