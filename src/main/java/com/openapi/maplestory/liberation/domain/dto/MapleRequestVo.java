package com.openapi.maplestory.liberation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapleRequestVo {

    String apikey;
    List<String> apiUrl = new ArrayList<>();
    String baseUrl;
    String ocid;
    String name;
    String date;
    //0, 1, 1.5, 2, 2.5, 3, 4, hyperpassive, hyperactive, 5, 6
    String character_skill_grade;

    public void setApiUrl(String url) {
        apiUrl.add(url);
    }

    public void resetApiUrl() {
        apiUrl.clear();
    }
}
