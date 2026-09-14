package com.url.LinkSnap.services;

import com.url.LinkSnap.Security.jwt.JwtAuthenticationResponse;
import com.url.LinkSnap.Security.jwt.JwtUtils;
import com.url.LinkSnap.dto.requestdto.LoginRequestDto;
import com.url.LinkSnap.dto.responsedto.UserResponseDto;
import com.url.LinkSnap.entities.User;
import com.url.LinkSnap.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder ;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse  authenticateUser(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwtToken);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name).orElseThrow(()-> new UsernameNotFoundException("user not found with username "+ name));
    }
}
