package com.openapi.maplestory.liberation.domain.controller;

import com.openapi.maplestory.liberation.domain.dto.ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InitController {


    @GetMapping("/hello")
    public ResponseEntity<ResponseVo> initApi(@RequestParam(value = "input") String input){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setUcd("00");
        responseVo.setMessage("수신 된 값 : " + input);
        return ResponseEntity.ok(responseVo);
    }

    @GetMapping("/testOpenAPI")
    public ResponseEntity<ResponseVo> testOpenAPI(@RequestParam(value = "name") String name) {

        String apikey = "test";
        String type = "json";
        String date = "";

        WebClient webClient = WebClient.builder()
                .baseUrl("http://open.api.nexon.com/maplestory")
                .defaultHeader("x-nxopen-api-key","test_20d5892d17fbffcdbb4b6c8d34a5943124807b95b4f783779eea66850ce570b207887955e11ecb37b3264b7471d00004")
                .build();

        String apiUrl = "/v1/id?character_name={name}";
        String testResponse = webClient
                .get()
                .uri(apiUrl,name)
                .retrieve()
                .bodyToFlux(String.class)
                .blockFirst();
        log.info("response : " + testResponse);

        ResponseVo responseVo = new ResponseVo();


        if(testResponse == null){
            responseVo.setUcd("99");
            responseVo.setMessage("ocid 조회 실패");
        }else {
            responseVo.setUcd("00");
            responseVo.setMessage(testResponse);
          }

        return ResponseEntity.ok(responseVo);
    }

}
