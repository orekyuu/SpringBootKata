package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
