package net.orekyuu.springbootkata.component;

import org.springframework.stereotype.Component;

@Component
public class CountUpService {

    private int count;

    public void increment() {
        count++;
    }

    public int currentCount() {
        return count;
    }
}
