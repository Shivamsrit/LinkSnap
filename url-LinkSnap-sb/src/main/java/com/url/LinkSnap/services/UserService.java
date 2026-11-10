package com.url.LinkSnap.services;

import com.url.LinkSnap.Security.jwt.JwtAuthenticationResponse;
import com.url.LinkSnap.dto.requestdto.LoginRequestDto;
import com.url.LinkSnap.dto.responsedto.UserResponseDto;
import com.url.LinkSnap.entities.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    public User registerUser(User user);
    public JwtAuthenticationResponse authenticateUser(LoginRequestDto loginRequestDto);

    User findByUserName(String name);
}
