package com.hoangit3.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class HelloController {
    @GetMapping("hello1")
    public String hello() {
        return "Hello 1";
    }
}
