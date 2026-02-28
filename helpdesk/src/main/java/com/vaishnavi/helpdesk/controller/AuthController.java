package com.vaishnavi.helpdesk.controller;

import com.vaishnavi.helpdesk.dto.LoginRequest;
import com.vaishnavi.helpdesk.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}