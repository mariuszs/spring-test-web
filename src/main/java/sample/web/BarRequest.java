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
@Scope(value = "request")
@Component
public class BarRequest {

    private static final Logger LOG = LoggerFactory.getLogger(BarRequest.class);

    int random;

    @PostConstruct
    public void started() {
        LOG.info("Request bean created!");
        random = new Random().nextInt();
    }

    public int random() {
        return random;
    }
}
