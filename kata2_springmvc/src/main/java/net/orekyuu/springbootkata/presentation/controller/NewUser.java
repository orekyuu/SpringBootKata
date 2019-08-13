package net.orekyuu.springbootkata.presentation.controller;

import net.orekyuu.springbootkata.domain.model.user.User;

public class NewUser {
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
