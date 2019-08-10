package net.orekyuu.springbootkata.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CountUpService {

    private int count;

    public void increment() {
        count++;
    }

    public int currentCount() {
        return count;
    }
}
