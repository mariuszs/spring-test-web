package sample.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author Mariusz Smykula
 */
@Scope(value = "prototype")
@Component
public class MyPrototypeBean {

    private static final Logger LOG = LoggerFactory.getLogger(MyPrototypeBean.class);

    private int value;

    @PostConstruct
    public void started() {
        LOG.info("Prototype bean created!");

        value = new Random().nextInt();
    }

    public int getValue() {
        return value;
    }
}
