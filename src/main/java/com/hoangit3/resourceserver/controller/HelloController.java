package com.hoangit3.resourceserver.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class HelloController {
    @GetMapping("hello1")
    public String hello() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hello 1" + principal.toString();
    }
}
