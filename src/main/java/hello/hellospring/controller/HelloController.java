package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Spring은 @Controller 해줘야함
@Controller
public class HelloController {

    // 웹어플리케이션에서 (주소 맨 뒤에)/hello 가 들어오면 이 메서드를 실행해줌
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        // templates 폴더의 hello.html 파일로 연결
        return "hello";
    }

}
