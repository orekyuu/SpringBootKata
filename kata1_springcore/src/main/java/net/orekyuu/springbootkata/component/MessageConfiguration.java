package net.orekyuu.springbootkata.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    @Value("${kata.message}")
    private String message = "default";

    @Bean
    public MessageDecorator mesasMessageDecorator() {
        return new MessageDecorator(message);
    }
}
