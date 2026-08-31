package com.url.LinkSnap.dto;

import com.url.LinkSnap.dto.requestdto.LoginRequestDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private Integer clickCounts ;
    private LocalDateTime createdDate;
    private String username;


}
