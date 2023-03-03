package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // "/" 는 첫번째로 호출할 수 있도록 해 줌
    @GetMapping("/")
    public String home() {
        return "home";
    }

}
