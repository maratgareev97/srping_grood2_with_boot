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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDAOInterface userDAOInterface;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.index());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "user/new";

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") String id) {
        System.out.println("edit: " + id);
        model.addAttribute("user", userService.getUserById(Integer.parseInt(id)));
        System.out.println("Good   " + Integer.parseInt(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "admin/edit";
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");

        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/test")
    public String test() {
        return "admin/login";
    }
}
