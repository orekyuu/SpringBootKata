package net.orekyuu.springbootkata.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeComponent {

    private final String randomString = UUID.randomUUID().toString();

    public String randomString() {
        return randomString;
    }
}
