package sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Scope("request")
public class MainController {

    private final MyRequestBean bar;

    private final MySessionBean foo;

    @Autowired
    public MainController(MyRequestBean bar, MySessionBean foo) {
        this.bar = bar;
        this.foo = foo;
    }

    @RequestMapping
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("name", name);
        model.addAttribute("foo", foo.getValue());
        model.addAttribute("bar", bar.random());
        return "main";
    }

}
