package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.*;
import com.openapi.maplestory.liberation.domain.dto.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.dto.stat.AbilityVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HexaStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HyperStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.StatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class MapleService {
    public String getOcid(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        MapleResponseVo mapleResponseVo = webClient
                .get()
                .uri(mapleRequestVo.getApiUrl(), mapleRequestVo.getName())
                .retrieve()
                .bodyToMono(MapleResponseVo.class)
                .block();
        return mapleResponseVo.getOcid();
    }

    private WebClient createWebClient(MapleRequestVo mapleRequestVo) {
        return WebClient.builder()
                .baseUrl(mapleRequestVo.getBaseUrl())
                .defaultHeader("x-nxopen-api-key", mapleRequestVo.getApikey())
                .build();
    }

    public BasicVo getBasic(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        BasicVo basicVo = webClient
                .get()
                .uri(mapleRequestVo.getBasicUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(BasicVo.class)
                .block();
        return basicVo;
    }

    public StatVo getStat(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        StatVo statVo = webClient
                .get()
                .uri(mapleRequestVo.getStatUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(StatVo.class)
                .block();
        return statVo;
    }

    public HyperStatVo getHyperStat(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        HyperStatVo hyperStatVo = webClient
                .get()
                .uri(mapleRequestVo.getHyperStatUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(HyperStatVo.class)
                .block();
        return hyperStatVo;
    }

    public ItemVo getItem(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        ItemVo itemVo = webClient
                .get()
                .uri(mapleRequestVo.getItemUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(ItemVo.class)
                .block();
        return itemVo;
    }

    public AbilityVo getAbilityVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        AbilityVo abilityVo = webClient
                .get()
                .uri(mapleRequestVo.getAbilityUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(AbilityVo.class)
                .block();
        return abilityVo;
    }

    public CashItemEquipmentVo getCashItemVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        CashItemEquipmentVo cashItemEquipmentVo = webClient
                .get()
                .uri(mapleRequestVo.getCashItemUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(CashItemEquipmentVo.class)
                .block();
        return cashItemEquipmentVo;
    }

    public SymbolVo getSymbolVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        SymbolVo symbolVo = webClient
                .get()
                .uri(mapleRequestVo.getSymbolUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(SymbolVo.class)
                .block();
        return symbolVo;
    }

    public SetVo getSetVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        SetVo setVo = webClient
                .get()
                .uri(mapleRequestVo.getSetUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(SetVo.class)
                .block();
        return setVo;
    }

    public PetEquipmentVo getPetVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        PetEquipmentVo petEquipmentVo = webClient
                .get()
                .uri(mapleRequestVo.getPetUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(PetEquipmentVo.class)
                .block();
        return petEquipmentVo;
    }

    public CharacterSkillVo getSkillVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        CharacterSkillVo characterSkillVo = webClient
                .get()
                .uri(mapleRequestVo.getSkillUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(CharacterSkillVo.class)
                .block();
        return characterSkillVo;
    }

    public HexaStatVo getHexaVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        HexaStatVo hexaStatVo = webClient
                .get()
                .uri(mapleRequestVo.getHexaUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(HexaStatVo.class)
                .block();
        return hexaStatVo;
    }

    public UnionStatVo getUnionVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        UnionStatVo unionStatVo = webClient
                .get()
                .uri(mapleRequestVo.getUnionUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(UnionStatVo.class)
                .block();
        return unionStatVo;
    }

    public UnionArtifactVo getUnionArtifactVo(MapleRequestVo mapleRequestVo) {
        WebClient webClient = createWebClient(mapleRequestVo);
        UnionArtifactVo unionArtifactVo = webClient
                .get()
                .uri(mapleRequestVo.getUnionArtifactUrl(), mapleRequestVo.getOcid(), mapleRequestVo.getDate())
                .retrieve()
                .bodyToMono(UnionArtifactVo.class)
                .block();
        return unionArtifactVo;
    }


//    public List<MapleResponseVo> getAll(MapleRequestVo mapleRequestVo) {
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
