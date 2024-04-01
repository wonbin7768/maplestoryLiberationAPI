package com.openapi.maplestory.liberation.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

@Getter
@AllArgsConstructor
public class MessageDto {
    private String message;                 // 전달할 메시지
    private String redirectUri;             // 리다이렉트 URI
    private RequestMethod method;           // HTTP 요청 메서드
}
