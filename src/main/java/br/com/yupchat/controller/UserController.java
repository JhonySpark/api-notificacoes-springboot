package br.com.yupchat.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yupchat.dto.AuthRequestDto;
import br.com.yupchat.dto.UserBasicDto;
import br.com.yupchat.dto.UserDto;
import br.com.yupchat.service.UserService;
import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/auth/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<UserBasicDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("token", userService.login(authRequest));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserBasicDto> getMe(Principal principal) {
        return ResponseEntity.ok(userService.getUserDetails(principal.getName()));
    }
}
