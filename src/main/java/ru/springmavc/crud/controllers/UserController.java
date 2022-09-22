package ru.springmavc.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.springmavc.crud.dao.UserDAO;
import ru.springmavc.crud.dao.UserDAOInterface;
import ru.springmavc.crud.models.User;
import ru.springmavc.crud.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.index());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") String id) {
        model.addAttribute("user", userService.getUserById(Integer.parseInt(id)));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "user/edit";
        userService.updateUser(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("/test")
    public String test() {
        return "user/login";
    }
}
