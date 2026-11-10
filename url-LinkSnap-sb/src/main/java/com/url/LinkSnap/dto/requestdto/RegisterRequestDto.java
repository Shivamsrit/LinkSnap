package com.url.LinkSnap.dto.requestdto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequestDto {

    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
