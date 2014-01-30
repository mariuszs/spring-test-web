package sample;

import org.springframework.web.context.WebApplicationContext;
import sample.web.Foo;
import sample.web.MyRequestBean;
import sample.web.MySessionBean;
import sample.web.MyPrototypeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mariusz Smykula
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainControllerWithListenerTest.TestConfig.class)
@TestExecutionListeners({MainControllerWithListenerTest.WebContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class MainControllerWithListenerTest {

    @Autowired
    MyPrototypeBean myPrototypeBean;

    @Autowired
    MySessionBean mySessionBean;

    @Autowired
    MyRequestBean myRequestBean;

    @Autowired
    Foo foo;

    @Test
    public void shouldAutowireBeans() throws Exception {

        assertThat(myPrototypeBean).isNotNull();
        assertThat(mySessionBean).isNotNull();
        assertThat(myRequestBean).isNotNull();
        assertThat(foo).isNotNull();

    }

    public static class WebContextTestExecutionListener extends AbstractTestExecutionListener {
        @Override
        public void prepareTestInstance(TestContext testContext) {
            if (testContext.getApplicationContext() instanceof GenericApplicationContext) {
                GenericApplicationContext context = (GenericApplicationContext) testContext.getApplicationContext();
                ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
                beanFactory.registerScope(WebApplicationContext.SCOPE_REQUEST,
                        new SimpleThreadScope());
                beanFactory.registerScope(WebApplicationContext.SCOPE_SESSION,
                        new SimpleThreadScope());
            }
        }
    }

    @Configuration
    @ComponentScan("sample.web")
    public static class TestConfig {
    }
}
