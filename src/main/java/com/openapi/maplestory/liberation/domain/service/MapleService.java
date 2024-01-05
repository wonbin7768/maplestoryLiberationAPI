package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.FinalStatVo;
import com.openapi.maplestory.liberation.domain.dto.MapleRequestVo;
import com.openapi.maplestory.liberation.domain.dto.MapleResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MapleService {
    public String getOcid(MapleRequestVo mapleRequestVo) {
        MapleResponseVo mapleResponse = getMapleResponse(mapleRequestVo.getBaseUrl(), mapleRequestVo.getApikey(), mapleRequestVo.getApiUrl().getFirst(), mapleRequestVo.getName());
        return mapleResponse.getOcid();
    }


    public Mono<MapleResponseVo> getAll(MapleRequestVo mapleRequestVo) {
        List<String> apiUrlList = mapleRequestVo.getApiUrl();
        for (String s : apiUrlList) {
            System.out.println("s = " + s);
        }
        WebClient webClient = WebClient.builder()
                .baseUrl(mapleRequestVo.getBaseUrl())
                .defaultHeader("x-nxopen-api-key", mapleRequestVo.getApikey())
                .build();

        Mono<MapleResponseVo> result = Mono.empty();

        for (String apiUrl : apiUrlList) {
           Mono<MapleResponseVo> apiResponseMono = webClient.get()
                            .uri(apiUrl,mapleRequestVo.getOcid(),mapleRequestVo.getDate())
                            .retrieve()
                            .bodyToMono(MapleResponseVo.class);
           result = result.then(apiResponseMono);
        }
        result.subscribe(mapleResponse -> {
            System.out.println("mapleResponse = " + mapleResponse);
        });
        return result;
    }

    private MapleResponseVo getMapleResponse(String baseUrl ,String apiKey ,String apiUrl,String... uriVariables) {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-nxopen-api-key", apiKey)
                .build();
        Mono<MapleResponseVo> mapleResponseVoMono = webClient
                .get()
                .uri(apiUrl, uriVariables)
                .retrieve()
                .bodyToMono(MapleResponseVo.class);
        MapleResponseVo mapleResponse = mapleResponseVoMono.block();
        return mapleResponse;
    }
}
