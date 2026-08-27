package com.url.LinkSnap.controller;

import com.url.LinkSnap.dto.requestdto.LoginRequestDto;
import com.url.LinkSnap.dto.requestdto.RegisterRequestDto;
import com.url.LinkSnap.entities.User;
import com.url.LinkSnap.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(userService.authenticateUser(loginRequestDto),HttpStatus.OK);
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setUserName(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        user.setRoles("ROLE_USER");
        userService.registerUser(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }

}
