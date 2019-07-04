package net.orekyuu.springbootkata.component;

import org.springframework.stereotype.Component;

@Component
public class InMemoryUserService implements UserService {
    @Override
    public int userCount() {
        return 0;
    }
}
