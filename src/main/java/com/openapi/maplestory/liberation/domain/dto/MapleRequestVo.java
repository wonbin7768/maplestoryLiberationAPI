package com.openapi.maplestory.liberation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapleRequestVo {

    String apikey;
    String apiUrl;
    String basicUrl = "/v1/character/basic?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String statUrl = "/v1/character/stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String hyperStatUrl = "/v1/character/hyper-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String itemUrl = "/v1/character/item-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String abilityUrl = "/v1/character/ability?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String cashItemUrl = "/v1/character/cashitem-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String symbolUrl = "/v1/character/symbol-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String setUrl = "/v1/character/set-effect?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String petUrl = "/v1/character/pet-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String skillUrl = "/v1/character/skill?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}&character_skill_grade=0";
    String hexaUrl = "/v1/character/hexamatrix-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";
    String unionUrl = "/v1/user/union-raider?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}";



    String baseUrl;
    String ocid;
    String name;
    String date;
    //0, 1, 1.5, 2, 2.5, 3, 4, hyperpassive, hyperactive, 5, 6
    String character_skill_grade;

}
