package com.url.LinkSnap.dto.responsedto;

import com.url.LinkSnap.enums.Role;
import lombok.Data;

@Data

public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role ;
}

