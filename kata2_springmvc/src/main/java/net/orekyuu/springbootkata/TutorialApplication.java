package net.orekyuu.springbootkata;

import net.orekyuu.springbootkata.domain.model.user.User;
import net.orekyuu.springbootkata.infrastructure.inmemory.InMemoryUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TutorialApplication {

    private final InMemoryUserRepository repository;

    public TutorialApplication(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

    @PostConstruct
    void setup() {
        repository.save(new User(1L, "Yamada"));
        repository.save(new User(2L, "Ito"));
        repository.save(new User(3L, "Sudo"));
    }
}
