package com.ilyaselmabrouki.gateway.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('RH')")
    public Authentication getAuthentication(Authentication authentication){
        return authentication;
    }
}
