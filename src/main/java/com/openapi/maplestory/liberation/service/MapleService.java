package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.*;
import com.openapi.maplestory.liberation.domain.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.stat.AbilityVo;
import com.openapi.maplestory.liberation.domain.stat.HexaStatVo;
import com.openapi.maplestory.liberation.domain.stat.HyperStatVo;
import com.openapi.maplestory.liberation.domain.stat.StatVo;
import com.openapi.maplestory.liberation.repository.dto.MapleRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class MapleService {
    public String getOcid(MapleRequestDto mapleRequestDto){
        WebClient webClient = createWebClient(mapleRequestDto);
        MapleResponseVo mapleResponseVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getName())
                .retrieve()
                .onStatus(status -> status.is4xxClientError()
                                || status.is5xxServerError()
                        , clientResponse ->
                                clientResponse.bodyToMono(String.class)
                                        .map(body -> new RuntimeException(body)))
                .bodyToMono(MapleResponseVo.class)
                .block();
        return mapleResponseVo.getOcid();
    }

    private WebClient createWebClient(MapleRequestDto mapleRequestDto) {
        System.out.println("mapleRequestDto = " + mapleRequestDto);
        return WebClient.builder()
                .baseUrl(mapleRequestDto.getBaseUrl())
                .defaultHeader("x-nxopen-api-key", mapleRequestDto.getApikey())
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(30 * 1024 * 1024))
                .build();

    }

    public BasicVo getBasic(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        BasicVo basicVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(BasicVo.class)
                .block();
        return basicVo;
    }

    public StatVo getStat(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        StatVo statVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(StatVo.class)
                .block();
        return statVo;
    }

    public HyperStatVo getHyperStat(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        HyperStatVo hyperStatVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(HyperStatVo.class)
                .block();
        return hyperStatVo;
    }

    public ItemVo getItem(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        ItemVo itemVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(ItemVo.class)
                .block();
        return itemVo;
    }

    public AbilityVo getAbilityVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        AbilityVo abilityVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(AbilityVo.class)
                .block();
        return abilityVo;
    }

    public CashItemEquipmentVo getCashItemVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        CashItemEquipmentVo cashItemEquipmentVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(CashItemEquipmentVo.class)
                .block();
        return cashItemEquipmentVo;
    }

    public SymbolVo getSymbolVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        SymbolVo symbolVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(SymbolVo.class)
                .block();
        return symbolVo;
    }

    public SetVo getSetVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        SetVo setVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(SetVo.class)
                .block();
        return setVo;
    }

    public PetEquipmentVo getPetVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        PetEquipmentVo petEquipmentVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(PetEquipmentVo.class)
                .block();
        return petEquipmentVo;
    }

    public CharacterSkillVo getSkillVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        CharacterSkillVo characterSkillVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(CharacterSkillVo.class)
                .block();
        return characterSkillVo;
    }

    public HexaStatVo getHexaVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        HexaStatVo hexaStatVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(HexaStatVo.class)
                .block();
        return hexaStatVo;
    }

    public UnionStatVo getUnionVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        UnionStatVo unionStatVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(UnionStatVo.class)
                .block();
        return unionStatVo;
    }

    public UnionArtifactVo getUnionArtifactVo(MapleRequestDto mapleRequestDto) {
        WebClient webClient = createWebClient(mapleRequestDto);
        UnionArtifactVo unionArtifactVo = webClient
                .get()
                .uri(mapleRequestDto.getApiUrl(), mapleRequestDto.getOcid())
                .retrieve()
                .bodyToMono(UnionArtifactVo.class)
                .block();
        return unionArtifactVo;
    }


//    public List<MapleResponseVo> getAll(MapleRequestDto mapleRequestVo) {
//        List<String> apiUrlList = mapleRequestVo.getApiUrl();
//        WebClient webClient = WebClient.builder()
//                .baseUrl(mapleRequestVo.getBaseUrl())
//                .defaultHeader("x-nxopen-api-key", mapleRequestVo.getApikey())
//                .build();
//
//        return Flux.fromIterable(apiUrlList)
//                .flatMap(apiUrl -> webClient.get()
//                        .uri(apiUrl,mapleRequestVo.getOcid(),mapleRequestVo.getDate())
//                        .retrieve()
//                        .bodyToMono(MapleResponseVo.class))
//                .collectList()
//                .block();
//
//    }

//    public List<ItemEquipmentVo> calStat(int level, String characterClass, List<ItemEquipmentVo> items, List<SymbolEquipmentVo> symbols, HyperStatVo hyperStat, UnionStatVo union) {
//        int basicStat = (level * 5) + 18;
//        System.out.println("basicStat = " + basicStat);
//        for (ItemEquipmentVo item : items) {
//            System.out.println("item.getItem_name() = " + item.getItem_name());
//        }
//
//        return items;
//    }
}
