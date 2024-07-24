package com.openapi.maplestory.liberation.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapleRequestDto {

    String apikey;
    String apiUrl;

    String baseUrl;
    String ocid;
    String name;
    String date;

}
