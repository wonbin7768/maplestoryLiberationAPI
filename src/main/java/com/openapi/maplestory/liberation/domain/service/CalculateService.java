package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.BasicVo;
import com.openapi.maplestory.liberation.domain.dto.CharacterSkillVo;
import com.openapi.maplestory.liberation.domain.dto.UnionStatVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.dto.stat.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CalculateService {
    public void calStat(BasicVo basic, StatVo stat, ItemVo itemVo, CashItemEquipmentVo cashItemEquipmentVo, PetEquipmentVo petEquipmentVo, HyperStatVo hyperStatVo, AbilityVo abilityVo, SymbolVo symbolVo, SetVo setVo, CharacterSkillVo skillVo, HexaStatVo hexaStatVo, UnionStatVo unionStatVo) {
        //일단 주스탯 순수 스탯구하기
        int level = basic.getCharacter_level();
        String characterClass = basic.getCharacter_class();
        int pureStat = level * 5 + 18;
        System.out.println("pureStat = " + pureStat);
        //미적용 주스탯
        //심볼
        List<SymbolEquipmentVo> symbol = symbolVo.getSymbol();
        int totalSymbolLUK = 0;
        for (SymbolEquipmentVo symbolEquipmentVo : symbol) {
            int symbolLuk = Integer.parseInt(symbolEquipmentVo.getSymbol_luk());
            totalSymbolLUK += symbolLuk;
        }
        System.out.println("totalSymbolLUK = " + totalSymbolLUK);
        //하이퍼
        String usePresetNo = hyperStatVo.getUse_preset_no();
        List<HyperStatDetailVo> hyperStatPreset;
        String StHyperLUK = "";
        String StHyperDEX = "";
        if (Objects.equals(usePresetNo, "1")){
            hyperStatPreset = hyperStatVo.getHyper_stat_preset_1();
        }else if(Objects.equals(usePresetNo, "2")){
            hyperStatPreset = hyperStatVo.getHyper_stat_preset_2();
        }else{
            hyperStatPreset = hyperStatVo.getHyper_stat_preset_3();
        }
        for (HyperStatDetailVo hyperStatDetailVo : hyperStatPreset) {
            String statType = hyperStatDetailVo.getStat_type();
            String statIncrease = hyperStatDetailVo.getStat_increase();
            if(Objects.equals(statType, "LUK")){
                StHyperLUK = statIncrease;
            } else if (Objects.equals(statType, "DEX")) {
                StHyperDEX = statIncrease;
            }
        }
        int hyperLUK = Integer.parseInt(StHyperLUK.replaceAll("[^0-9]", ""));
        int hyperDEX = Integer.parseInt(StHyperDEX.replaceAll("[^0-9]", ""));
        System.out.println("hyperLUK = " + hyperLUK);
        System.out.println("hyperDEX = " + hyperDEX);
        //유니온

        List<String> unionRaiderStat = unionStatVo.getUnion_raider_stat();
        List<String> unionOccupiedStat = unionStatVo.getUnion_occupied_stat();

        System.out.println("symbol = " + symbol);
        System.out.println("usePresetNo = " + usePresetNo);
        System.out.println("unionRaiderStat = " + unionRaiderStat);
        System.out.println("unionOccupiedStat = " + unionOccupiedStat);

    }
}
