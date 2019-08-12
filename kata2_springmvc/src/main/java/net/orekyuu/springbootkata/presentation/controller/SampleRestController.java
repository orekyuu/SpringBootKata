package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class SampleRestController {

    final InMemoryUserRepository repository;

    public SampleRestController(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public User show(@PathVariable long id) {
        User user = repository.findById(id).orElse(null);
        return user;
    }

    @GetMapping
    public List<User> list() {
        List<User> users = repository.findAll();
        return users;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        repository.remove(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable long id, @RequestParam String name) {
        User user = new User(id, name);
        repository.save(user);
    }
}
