package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void show(@PathVariable long id) {
        User user = repository.findById(id).orElseThrow();
        // テンプレートはresources/templates/users/show.htmlに入ってます
    }

    @GetMapping
    public void list() {
        List<User> users = repository.findAll();
        // テンプレートはresources/templates/users/list.htmlに入ってます
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
