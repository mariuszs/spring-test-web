package sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sample.web.MyRequestBean;
import sample.web.MySessionBean;
import sample.web.MyPrototypeBean;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mariusz Smykula
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainControllerWithWebAppTest.TestConfig.class)
@WebAppConfiguration
public class MainControllerWithWebAppTest {

    @Autowired
    MyPrototypeBean myPrototypeBean;

    @Autowired
    MySessionBean mySessionBean;

    @Autowired
    MyRequestBean myRequestBean;

    @Test
    public void testName() throws Exception {

        assertThat(myPrototypeBean).isNotNull();
        assertThat(mySessionBean).isNotNull();
        assertThat(myRequestBean).isNotNull();
        assertThat(myRequestBean.random()).isNotNull();

    }

    @Configuration
    @ComponentScan("sample.web")
    public static class TestConfig {

    }


}
