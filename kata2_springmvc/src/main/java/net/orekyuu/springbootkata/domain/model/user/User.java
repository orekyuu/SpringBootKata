package net.orekyuu.springbootkata.domain.model.user;

public class User {
    final Long id;
    final String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
