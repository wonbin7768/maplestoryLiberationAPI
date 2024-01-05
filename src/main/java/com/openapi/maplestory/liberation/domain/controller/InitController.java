//package com.openapi.maplestory.liberation.domain.controller;
//
//import com.openapi.maplestory.liberation.domain.dto.MapleRequestVo;
//import com.openapi.maplestory.liberation.domain.dto.MapleResponseVo;
//import com.openapi.maplestory.liberation.domain.dto.ResponseVo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class InitController {
//
//
//    @GetMapping("/hello")
//    public ResponseEntity<ResponseVo> initApi(@RequestParam(value = "input") String input){
//        ResponseVo responseVo = new ResponseVo();
//        responseVo.setUcd("00");
//        responseVo.setMessage("수신 된 값 : " + input);
//        return ResponseEntity.ok(responseVo);
//    }
//
//    @GetMapping("/testOpenAPI")
//    public ResponseEntity<ResponseVo> testOpenAPI(@RequestParam(value = "name") String name) {
//
//        //API가 어제 기준으로 받아올수있기에 변환
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime yesterday = now.minusDays(1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
//        String date = yesterday.format(formatter);
//
//        //MapleRequestVo 정의
//        MapleRequestVo mapleRequestVo = new MapleRequestVo();
//        mapleRequestVo.setApikey("test_20d5892d17fbffcdbb4b6c8d34a5943124807b95b4f783779eea66850ce570b207887955e11ecb37b3264b7471d00004");
//        mapleRequestVo.setBaseUrl("http://open.api.nexon.com/maplestory");
//        mapleRequestVo.setApiUrl("/v1/id?character_name={name}");
//        mapleRequestVo.setName(name);
//        mapleRequestVo.setDate(date);
//
//        WebClient webClient = WebClient.builder()
//                .baseUrl(mapleRequestVo.getBaseUrl())
//                .defaultHeader("x-nxopen-api-key",mapleRequestVo.getApikey())
//                .build();
//
//        MapleResponseVo mapleResponse = webClient
//                .get()
//                .uri(
//                        mapleRequestVo.getApiUrl(),
//                        mapleRequestVo.getName()
//                )
//                .retrieve()
//                .bodyToFlux(MapleResponseVo.class)
//                .blockFirst();
//        log.info("response : " + mapleResponse);
//
//        ResponseVo responseVo = new ResponseVo();
//
//
//        if(mapleResponse == null){
//            responseVo.setUcd("99");
//            responseVo.setMessage("ocid 조회 실패");
//        }else {
//            responseVo.setUcd("00");
//            responseVo.setMessage(mapleResponse.toString());
//          }
//
//        return ResponseEntity.ok(responseVo);
//    }
//
//}
