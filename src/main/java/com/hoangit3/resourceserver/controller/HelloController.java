package com.hoangit3.resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class HelloController {
    @GetMapping("hello1")
    public String hello() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> decodedDetails = (LinkedHashMap<String, Object>)authenticationDetails.getDecodedDetails();
        return "Hello " + decodedDetails.get("userId");
    }
}
