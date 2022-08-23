package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Spring은 @Controller 해줘야함
@Controller
public class HelloController {

    // 웹어플리케이션에서 (주소 맨 뒤에)/hello 가 들어오면 이 메서드를 실행해줌
    @GetMapping("hello")
    public String hello(Model model) {
        // 값(hello!!)을 직접 불러옴
        model.addAttribute("data","hello!!");
        // templates 폴더의 hello.html 파일로 연결
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 값을 외부에서 url 파라미터로 받아옴
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    // http에서 Body부에 값을 직접 넣어주겠다는 의미
    // 웹 페이지에서 '소스 보기'로 코드를 보면 html 태그 없이 깔끔하게 값만 있음
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
