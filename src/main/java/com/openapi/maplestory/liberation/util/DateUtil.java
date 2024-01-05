package com.openapi.maplestory.liberation.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    static public String getDate(){
        //API가 어제 기준으로 받아올수있기에 변환
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        String date = yesterday.format(formatter);
        return date;

    }

}
