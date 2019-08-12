package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class SampleRestController {

    final InMemoryUserRepository repository;

    public SampleRestController(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    public void show(long id) {
        User user = repository.findById(id).orElse(null);
    }

    public void list() {
        List<User> users = repository.findAll();
    }

    public void delete(long id) {
        repository.remove(id);
    }

    public void update(long id, String name) {
        User user = new User(id, name);
        repository.save(user);
    }
}
