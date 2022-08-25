package ru.springmavc.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.springmavc.crud.dao.UserDAO;
import ru.springmavc.crud.models.User;
import ru.springmavc.crud.service.UserService;
import ru.springmavc.crud.service.UserServiceImpl;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String pageForUser (Model model, Principal principal) {
        model.addAttribute("user",userService.getUserByLogin(principal.getName()));
        return "user";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}

//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Autowired
//    private UserDAOInterface userDAOInterface;
//
//    @Autowired
//    private UserServiceImpl userService;
//
//// Необходимо без спринга
////    public UserController(UserDAO userDAO, UserDAOInterface userDAOInterface, UserServiceImpl userService) {
////        this.userDAO = userDAO;
////        this.userDAOInterface = userDAOInterface;
////        this.userService = userService;
////    }
//
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("user", userService.index());
//        return "user/index";
//    }
//
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "user/show";
//    }
//
//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "user/new";
//    }
//
//    //    @Transactional
//    @PostMapping()
//    public String create(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "user/new";
//
//        userService.saveUser(user);
//        return "redirect:/user";
//    }
//
//    //    @GetMapping("/{id}/edit")
//    @GetMapping("/edit")
//    //public String edit(Model model, @PathVariable("id") int id) {
//    public String edit(Model model, @RequestParam("id") String id) {
//        System.out.println("edit: " + id);
//        model.addAttribute("user", userService.getUserById(Integer.parseInt(id)));
//        System.out.println("Good   " + Integer.parseInt(id));
//        return "user/edit";
//    }
//
//    @PatchMapping("/{id}")
////    @PatchMapping("edit?id={id}")
//    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @PathVariable("id") int id) {
////    @GetMapping("/")
////    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
////                         @RequestParam("id") int id) {
//        if (bindingResult.hasErrors())
//            return "user/edit";
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//
//        userService.updateUser(id, user);
//        return "redirect:/user";
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
////    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
////    @PostMapping("/{id}")
//        //к@ResponseBody
////    @DeleteMapping("/delete")
////    public String delete(@RequestParam("id") String id) {
//        System.out.println("DELETE");
//        userService.deleteUser(id);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/test")
//    public String test(@RequestParam("name") String name) {
//        userService.test();
//        System.out.println(name);
//        return "user/test";
//    }
//}
