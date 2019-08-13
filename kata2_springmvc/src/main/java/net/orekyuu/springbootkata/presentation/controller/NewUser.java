package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;

import javax.validation.constraints.Size;

public class NewUser {
    @Size(min = 3, max = 10)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User toUser() {
        return new User(null, name);
    }
}
