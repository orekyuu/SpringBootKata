package net.orekyuu.springbootkata.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String message = "default";

    @Bean
    public MessageDecorator mesasMessageDecorator() {
        return new MessageDecorator(message);
    }
}
