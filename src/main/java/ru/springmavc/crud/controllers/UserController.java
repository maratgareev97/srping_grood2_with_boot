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
    private final UserDAO userDAO;

    @Autowired
    private final UserDAOInterface userDAOInterface;

    @Autowired
    private final UserServiceImpl userService;

    public UserController(UserDAO userDAO, UserDAOInterface userDAOInterface, UserServiceImpl userService) {
        this.userDAO = userDAO;
        this.userDAOInterface = userDAOInterface;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.index());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    //    @Transactional
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/new";

        userService.save(user);
        return "redirect:/user";
    }

    //    @GetMapping("/{id}/edit")
    @GetMapping("/edit")
    //public String edit(Model model, @PathVariable("id") int id) {
    public String edit(Model model, @RequestParam("id") String id) {
        System.out.println("edit: " + id);
        model.addAttribute("user", userService.show(Integer.parseInt(id)));
        System.out.println("Good   " + Integer.parseInt(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
//    @PatchMapping("edit?id={id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
//    @GetMapping("/")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "user/edit";
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");

        userService.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
//    @PostMapping("/{id}")
        //@ResponseBody
//    @DeleteMapping("/delete")
//    public String delete(@RequestParam("id") String id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/test")
    public String test(@RequestParam("name") String name) {
        userService.test();
        System.out.println(name);
        return "user/test";
    }
}
