package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
