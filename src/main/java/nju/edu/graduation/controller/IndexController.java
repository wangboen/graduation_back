package nju.edu.graduation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/test")
    public String test() {
        return "测试前后端交互";
    }
}
