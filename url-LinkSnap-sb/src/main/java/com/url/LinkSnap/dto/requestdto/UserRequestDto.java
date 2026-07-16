package com.url.LinkSnap.dto.requestdto;

import com.url.LinkSnap.enums.Role;
import lombok.Data;


@Data
public class UserRequestDto {

    private String name;
    private String email;
    private String password;
    private Role role ;
}

