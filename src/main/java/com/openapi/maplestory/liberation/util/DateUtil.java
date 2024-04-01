package com.openapi.maplestory.liberation.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateUtil {

    static public String getDate(){
        //API가 어제 기준으로 받아올수있기에 변환
        LocalDateTime now = LocalDateTime.now();
        TimeZone tzSeoul = TimeZone.getTimeZone("Asia/Seoul");
        LocalDateTime yesterday = now.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(tzSeoul.toZoneId());
        String date = yesterday.format(formatter);
        System.out.println("now = " + now);
        System.out.println("yesterday = " + yesterday);
        System.out.println("date = " + date);
        return date;
    }
}
