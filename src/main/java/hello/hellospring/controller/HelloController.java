package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String HelloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 글자 그대로 내린다
    public String helloString(@RequestParam("name") String name) {
        return "hello "+ name; // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 기본적으로 json (jackson2)방식으로 처리하지만 바꿀 수 있음
    }

    static class Hello {
        private String name;

    // generator 단축키 alt insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
