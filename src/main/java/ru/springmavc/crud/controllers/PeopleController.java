package ru.springmavc.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.springmavc.crud.dao.UsersDAO;
import ru.springmavc.crud.dao.UsersDAOInterface;
import ru.springmavc.crud.models.Users;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private final UsersDAO usersDAO;

    @Autowired
    private final UsersDAOInterface usersDAOInterface;

    public PeopleController(UsersDAO usersDAO, UsersDAOInterface usersDAOInterface) {
        this.usersDAO = usersDAO;
        this.usersDAOInterface = usersDAOInterface;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", usersDAOInterface.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", usersDAOInterface.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newUsers(@ModelAttribute("users") Users users) {
        return "people/new";
    }

//    @Transactional
    @PostMapping()
    public String create(@ModelAttribute("users") @Valid Users users,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        usersDAOInterface.save(users);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("users", usersDAOInterface.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid Users users, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        usersDAOInterface.update(id, users);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersDAOInterface.delete(id);
        return "redirect:/people";
    }
}