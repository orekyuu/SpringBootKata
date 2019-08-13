package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class ThymeleafController {
    final InMemoryUserRepository repository;

    public ThymeleafController(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public String show(@PathVariable long id, Model model) {
        User user = repository.findById(id).orElseThrow();
        // テンプレートはresources/templates/users/show.htmlに入ってます
        model.addAttribute("user", user);
        return "users/show";
    }

    @GetMapping
    public String list(Model model) {
        List<User> users = repository.findAll();
        // テンプレートはresources/templates/users/list.htmlに入ってます
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("registration")
    public String showRegistration() {
        return "users/registration";
    }

    @PostMapping("registration")
    public String registration(NewUser newUser) {
        repository.save(newUser.toUser());
        return "";
    }

    public NewUser newUser() {
        return new NewUser();
    }
}
