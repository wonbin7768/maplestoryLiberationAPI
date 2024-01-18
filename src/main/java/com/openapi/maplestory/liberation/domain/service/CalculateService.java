package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.BasicVo;
import com.openapi.maplestory.liberation.domain.dto.CharacterSkillVo;
import com.openapi.maplestory.liberation.domain.dto.UnionStatVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemOptionVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemPresetVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.dto.stat.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class CalculateService {
    public List<Integer> calUnAppliedStat(HyperStatVo hyperStatVo,SymbolVo symbolVo, UnionStatVo unionStatVo, HexaStatVo hexaStatVo) {

        //미적용 주스탯
        //심볼
        List<SymbolEquipmentVo> symbol = symbolVo.getSymbol();
        int totalSymbolLUK = 0;
        for (SymbolEquipmentVo symbolEquipmentVo : symbol) {
            int symbolLuk = Integer.parseInt(symbolEquipmentVo.getSymbol_luk());
            totalSymbolLUK += symbolLuk;
        }
//        System.out.println("totalSymbolLUK = " + totalSymbolLUK);
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
//        System.out.println("hyperLUK = " + hyperLUK);
//        System.out.println("hyperDEX = " + hyperDEX);
        //유니온
        int unionLUK = calUnionStat(unionStatVo.getUnion_raider_stat(),"LUK") +
                calUnionStat(unionStatVo.getUnion_occupied_stat(),"LUK");
        int unionDEX = calUnionStat(unionStatVo.getUnion_raider_stat(),"DEX") +
                calUnionStat(unionStatVo.getUnion_occupied_stat(),"DEX");

        //헥사스탯
        int hexaStat = calHexaStat(hexaStatVo);
        System.out.println("순수 핵사 주스텟 = " + hexaStat);

//        System.out.println("unionLUK = " + unionLUK);
//        System.out.println("unionDEX = " + unionDEX);
//        System.out.println("symbol = " + symbol);
//        System.out.println("usePresetNo = " + usePresetNo);


        List<Integer> zipUnAppliedStat = new ArrayList<>();
        int unAppliedLUK = totalSymbolLUK + hyperLUK + unionLUK + hexaStat;
        int unAppliedDEX = hyperDEX + unionDEX;
        zipUnAppliedStat.add(unAppliedLUK);
        zipUnAppliedStat.add(unAppliedDEX);

//        System.out.println("unAppliedLUK = " + unAppliedLUK);
//        System.out.println("unAppliedDEX = " + unAppliedDEX);
//        System.out.println("zipUnAppliedStat = " + zipUnAppliedStat);


        return zipUnAppliedStat;
    }
    public List<Integer> calAppliedStat(BasicVo basic, StatVo stat, ItemVo itemVo, CashItemEquipmentVo cashItemEquipmentVo,  AbilityVo abilityVo, SetVo setVo, CharacterSkillVo skillVo,String date) throws ParseException {
        List<Integer> zipAppliedStat = new ArrayList<>();
        //일단 주스탯 순수 스탯구하기
        int level = basic.getCharacter_level();
        String characterClass = basic.getCharacter_class();
        int pureStat = level * 5 + 18;
        System.out.println("pureStat = " + pureStat);
        //캐시장비
        //int presetNo = cashItemEquipmentVo.getPreset_no();
        int presetNo = 2;
        List<CashItemPresetVo> cashItemEquipmentPreset;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate1 = format.parse(date);
        Date cashDate;
        if(presetNo == 1){
            cashItemEquipmentPreset = cashItemEquipmentVo.getCash_item_equipment_preset_1();
            cashDate = format.parse(cashItemEquipmentVo.getCash_item_equipment_preset_1().get(0).getDate_option_expire());
        }else if(presetNo == 2){
            cashItemEquipmentPreset = cashItemEquipmentVo.getCash_item_equipment_preset_2();
            cashDate = format.parse(cashItemEquipmentVo.getCash_item_equipment_preset_2().get(0).getDate_option_expire());
        }else{
            cashItemEquipmentPreset = cashItemEquipmentVo.getCash_item_equipment_preset_3();
            cashDate = format.parse(cashItemEquipmentVo.getCash_item_equipment_preset_3().get(0).getDate_option_expire());
        }
        int cashItemLuk = 0;
        for (CashItemPresetVo cashItemPresetVo : cashItemEquipmentPreset) {
            System.out.println("cashItemPresetVo = " + cashItemPresetVo.getCashItemOptionVo());
             if (nowDate1.before(cashDate)) {
                 List<CashItemOptionVo> cashItemOptionVo = cashItemPresetVo.getCashItemOptionVo();
                 for (CashItemOptionVo itemOptionVo : cashItemOptionVo) {
                     String optionType = itemOptionVo.getOption_type();
                     int optionValue = Integer.parseInt(itemOptionVo.getOption_value());
                     if(Objects.equals(optionType,"LUK")){
                         cashItemLuk += optionValue;
                     }
                 }
                 System.out.println("캐시장비 유효기간 안지남");
            } else {
                System.out.println("지남");
            }
        }
        System.out.println("cashItemLuk = " + cashItemLuk);

        return zipAppliedStat;
    }
    private int calUnionStat(List<String> statList,String statType){
        int calStat = 0;
        for (String statValue : statList) {
            if(statValue.contains(statType)) {
                String deleteStr = statValue.replaceAll("[^0-9]", "");
                calStat += Integer.parseInt(deleteStr);
            }
        }
        return calStat;
    }
    private int calHexaStat(HexaStatVo hexaStatVo){
        List<HexaStatDetailVo> hexaStatDetailVo = hexaStatVo.getHexaStatDetailVo();
        int stat = 0;
        for (HexaStatDetailVo statDetailVo : hexaStatDetailVo) {
            String mainStatName = statDetailVo.getMain_stat_name();
            String subStatName1 = statDetailVo.getSub_stat_name_1();
            String subStatName2 = statDetailVo.getSub_stat_name_2();
            int mainStatLevel = statDetailVo.getMain_stat_level();
            int subStatLevel1 = statDetailVo.getSub_stat_level_1();
            int subStatLevel2 = statDetailVo.getSub_stat_level_2();

            if (Objects.equals(mainStatName,"주력 스탯 증가")){
                stat = getMainStat(mainStatLevel);
            }
            if (Objects.equals(subStatName1,"주력 스탯 증가")){
                stat = 100 * subStatLevel1;
            } else if (Objects.equals(subStatName2, "주력 스탯 증가")) {
                stat = 100 * subStatLevel2;
            }
        }
        return stat;
    }
    public int getMainStat(int mainStatLevel) {
        Map<Integer, Integer> statLevels = new HashMap<>();
        statLevels.put(1, 100);
        statLevels.put(2, 200);
        statLevels.put(3, 300);
        statLevels.put(4, 400);
        statLevels.put(5, 600);
        statLevels.put(6, 800);
        statLevels.put(7, 1000);
        statLevels.put(8, 1300);
        statLevels.put(9, 1600);
        statLevels.put(10, 2000);

        return statLevels.getOrDefault(mainStatLevel, 0);
    }

}
