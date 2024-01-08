package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.MapleRequestVo;
import com.openapi.maplestory.liberation.domain.dto.MapleResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class MapleService {
    public String getOcid(MapleRequestVo mapleRequestVo) {
        MapleResponseVo mapleResponse = getMapleResponse(mapleRequestVo.getBaseUrl(), mapleRequestVo.getApikey(), mapleRequestVo.getApiUrl().getFirst(), mapleRequestVo.getName());
        return mapleResponse.getOcid();
    }


    public Mono<List<MapleResponseVo>> getAll(MapleRequestVo mapleRequestVo) {
        List<String> apiUrlList = mapleRequestVo.getApiUrl();
        WebClient webClient = WebClient.builder()
                .baseUrl(mapleRequestVo.getBaseUrl())
                .defaultHeader("x-nxopen-api-key", mapleRequestVo.getApikey())
                .build();

        return Flux.fromIterable(apiUrlList)
                .flatMap(apiUrl -> webClient.get()
                        .uri(apiUrl,mapleRequestVo.getOcid(),mapleRequestVo.getDate())
                        .retrieve()
                        .bodyToMono(MapleResponseVo.class))
                .collectList();

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
