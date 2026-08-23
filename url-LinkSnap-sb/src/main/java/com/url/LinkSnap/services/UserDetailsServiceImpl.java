package com.url.LinkSnap.Security.jwt;

import com.url.LinkSnap.entities.User;
import com.url.LinkSnap.repositories.UserRepository;
import com.url.LinkSnap.services.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        User user = (User) userRepository.findByUserName(username).
                orElseThrow(()->new UsernameNotFoundException("User Not Found"+username));
        return UserDetailsImpl.build(user);
    }
}
