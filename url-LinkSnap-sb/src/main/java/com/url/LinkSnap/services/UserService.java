package com.url.LinkSnap.services;

import com.url.LinkSnap.dto.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {
    void getUser();
    List<UserResponseDto> getAllUser();



}
