package com.openapi.maplestory.liberation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseVo {

    String ucd;
    String message;

    public ResponseVo() {

    }
}
