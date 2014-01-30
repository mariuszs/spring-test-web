package sample.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Mariusz Smykula
 */
@Configuration
public class FooConfig {
    @Bean
    @Scope("request")
    public Foo foo() {
        return new Foo();
    }
}
