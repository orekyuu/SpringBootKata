package net.orekyuu.springbootkata.component;

import org.springframework.stereotype.Component;

@Component
public class SimpleSumService implements SumService {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
