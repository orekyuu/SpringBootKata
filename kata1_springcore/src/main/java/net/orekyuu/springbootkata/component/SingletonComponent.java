package net.orekyuu.springbootkata.component;

import org.springframework.stereotype.Component;

@Component
public class SingletonComponent {

    final PrototypeComponent prototypeComponent;

    public SingletonComponent(PrototypeComponent prototypeComponent) {
        this.prototypeComponent = prototypeComponent;
    }

    public String generateRandomString() {
        return prototypeComponent.randomString();
    }
}
