package sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sample.web.MyPrototypeBean;
import sample.web.MyRequestBean;
import sample.web.MySessionBean;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Mariusz Smykula
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainControllerTest.TestConfig.class)
@WebAppConfiguration
public class MainControllerTest {

    @Autowired
    MyPrototypeBean myPrototypeBean;

    @Autowired
    MySessionBean mySessionBean;

    @Autowired
    MyRequestBean myRequestBean;

    @Autowired
    Foo foo;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    MockHttpServletRequest request;

    @Autowired
    MockHttpSession session;

    @Test
    public void testName() throws Exception {

        assertThat(myPrototypeBean).isNotNull();
        assertThat(mySessionBean).isNotNull();
        assertThat(myRequestBean).isNotNull();
        assertThat(myRequestBean.random()).isNotNull();

    }

    @Test
    public void requestScope() throws Exception {
        assertThat(myRequestBean).isSameAs(request.getAttribute("myRequestBean"));
        assertThat(myRequestBean).isSameAs(wac.getBean("myRequestBean", MyRequestBean.class));
    }

    @Test
    public void sessionScope() throws Exception {
        assertThat(mySessionBean).isSameAs(session.getAttribute("mySessionBean"));
        assertThat(mySessionBean).isSameAs(wac.getBean("mySessionBean", MySessionBean.class));
    }

    @Test
    public void sessionScopeWithFoo() throws Exception {
        assertThat(foo).isNotNull();
        assertThat(foo).isSameAs(request.getAttribute("foo"));
        assertThat(foo).isSameAs(wac.getBean("foo", Foo.class));
    }

    @Configuration
    @ComponentScan("sample.web")
    public static class TestConfig {

    }


}
