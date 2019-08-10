package net.orekyuu.springbootkata.component;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

    public String hello() {
        return "hello";
    }
}
