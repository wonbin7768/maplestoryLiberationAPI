package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.*;
import com.openapi.maplestory.liberation.domain.dto.equipment.*;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemBaseVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemOptionVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetEffectInfoVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetEffectVo;
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
        int unionLUK = calUnionStat(unionStatVo.getUnion_raider_stat(),"LUK");
        int unionDEX = calUnionStat(unionStatVo.getUnion_raider_stat(),"DEX");

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
        System.out.println("zipUnAppliedStat = " + zipUnAppliedStat);


        return zipUnAppliedStat;
    }
    public List<Integer> calAppliedStat(BasicVo basic, StatVo stat, ItemVo itemVo, CashItemEquipmentVo cashItemEquipmentVo, AbilityVo abilityVo, SetVo setVo, CharacterSkillVo skillVo, String date , UnionStatVo unionStatVo, UnionArtifactVo unionArtifactVo) throws ParseException {
        List<Integer> zipAppliedStat = new ArrayList<>();
        //일단 주스탯 순수 스탯구하기
        int level = basic.getCharacter_level();
        String characterClass = basic.getCharacter_class();
        int pureStat = level * 5 + 18;
        System.out.println("pureStat = " + pureStat);
        //캐시장비
        List<CashItemBaseVo> cashItemEquipmentBase = cashItemEquipmentVo.getCash_item_equipment_base();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate1 = format.parse(date);
        int cashItemLuk = 0;
        for (CashItemBaseVo cashItemBaseVo : cashItemEquipmentBase) {
            System.out.println("cashItemPresetVo = " + cashItemBaseVo.getCashItemOptionVo());
                 List<CashItemOptionVo> cashItemOptionVo = cashItemBaseVo.getCashItemOptionVo();
                 for (CashItemOptionVo itemOptionVo : cashItemOptionVo) {
                     String optionType = itemOptionVo.getOption_type();
                     int optionValue = Integer.parseInt(itemOptionVo.getOption_value());
                     if(Objects.equals(optionType,"LUK")){
                         cashItemLuk += optionValue;
                     }
                 }
        }
        System.out.println("cashItemLuk = " + cashItemLuk);
//        List<AbilityInfo> abilityInfo = abilityVo.getAbility_info();
//        for (AbilityInfo info : abilityInfo) {
//            System.out.println("info = " + info);
//        }
        List<FinalStatVo> finalStat = stat.getFinal_stat();
        for (FinalStatVo finalStatVo : finalStat) {
            System.out.println("finalStatVo = " + finalStatVo);
        }
        //셋트옵션 스탯 구하기
        int calSetStat = 0;
        List<SetEffectVo> setEffect = setVo.getSet_effect();
        for (SetEffectVo setEffectVo : setEffect) {
            System.out.println("setEffectVo = " + setEffectVo);
            List<SetEffectInfoVo> setEffectInfoVo = setEffectVo.getSetEffectInfoVo();
            for (SetEffectInfoVo effectInfoVo : setEffectInfoVo) {
                String setOption = effectInfoVo.getSet_option();
                if (setOption.contains("올스탯")) {
                    int statIDX = setOption.indexOf("올스탯");
                    String subStringSetOption = setOption.substring(statIDX, (statIDX+10));
                    String deleteStr = subStringSetOption.replaceAll("[^0-9]", "");
                    calSetStat += Integer.parseInt(deleteStr);
                }else if(setOption.contains("LUK")){
                    int statIDX = setOption.indexOf("LUK");
                    String subStringSetOption = setOption.substring(statIDX, (statIDX+10));
                    String deleteStr = subStringSetOption.replaceAll("[^0-9]", "");
                    calSetStat += Integer.parseInt(deleteStr);
                }
            }
        }
        System.out.println("calSetStat = " + calSetStat);
        int titleStat = 0;
        Title title = itemVo.getTitle();
        Date titleDate;
        if(title.getDate_option_expire() == null){
            String titleDescription = title.getTitle_description();
            int statIDX = titleDescription.indexOf("올스탯");
            String subStringSetOption = titleDescription.substring(statIDX, (statIDX+10));
            String deleteStr = subStringSetOption.replaceAll("[^0-9]", "");
            titleStat += Integer.parseInt(deleteStr);
        }else{
            titleDate = format.parse(title.getDate_option_expire());
            if (nowDate1.before(titleDate)){
                String titleDescription = title.getTitle_description();
                int statIDX = titleDescription.indexOf("올스탯");
                String subStringSetOption = titleDescription.substring(statIDX, (statIDX+10));
                String deleteStr = subStringSetOption.replaceAll("[^0-9]", "");
                titleStat += Integer.parseInt(deleteStr);            }
        }
        System.out.println("titleStat = " + titleStat);
        int itemLUK = 0;
        int itemDEX = 0;
        int itemAllStat = 0;
        int weaponLUK = 0;
        int weaponDEX = 0;
        int weaponAllStat = 0;
        int percentLUK = 0;
        int justOptionLUK = 0;
        int percentDEX = 0;
        int justOptionDEX = 0;
        int percentAllStat = 0;
        int justOptionAllStat = 0;

        List<ItemEquipmentVo> itemEquipment = itemVo.getItem_equipment();
        for (ItemEquipmentVo itemEquipmentVo : itemEquipment) {
            ItemTotalOptionVo itemTotalOption = itemEquipmentVo.getItemTotalOption();
            String itemEquipmentPart = itemEquipmentVo.getItem_equipment_part();
            String potentialOption1 = itemEquipmentVo.getPotential_option_1();
            String potentialOption2 = itemEquipmentVo.getPotential_option_2();
            String potentialOption3 = itemEquipmentVo.getPotential_option_3();
            String additionalPotentialOption1 = itemEquipmentVo.getAdditional_potential_option_1();
            String additionalPotentialOption2 = itemEquipmentVo.getAdditional_potential_option_2();
            String additionalPotentialOption3 = itemEquipmentVo.getAdditional_potential_option_3();
            ItemExceptionalOptionVo itemExceptionalOption = itemEquipmentVo.getItem_exceptional_option();
            String soulOption = itemEquipmentVo.getSoul_option();
            String totLUK = itemTotalOption.getLuk();
            String totDEX = itemTotalOption.getDex();
            String totAllStat = itemTotalOption.getAll_stat();
                itemLUK += Integer.parseInt(totLUK);
                itemDEX += Integer.parseInt(totDEX);
                itemAllStat += Integer.parseInt(totAllStat);

                    percentLUK += calItemPercentOption(potentialOption1,"LUK");
                    percentDEX += calItemPercentOption(potentialOption1,"DEX");
                    percentAllStat += calItemPercentOption(potentialOption1,"올스탯");
                    justOptionLUK += calItemJustStatOption(potentialOption1, "LUK" , level);
                    justOptionDEX += calItemJustStatOption(potentialOption1, "DEX", level);
                    justOptionAllStat += calItemJustStatOption(potentialOption1, "올스탯", level);

                    percentLUK += calItemPercentOption(potentialOption2,"LUK");
                    percentDEX += calItemPercentOption(potentialOption2,"DEX");
                    percentAllStat += calItemPercentOption(potentialOption2,"올스탯");
                    justOptionLUK += calItemJustStatOption(potentialOption2, "LUK", level);
                    justOptionDEX += calItemJustStatOption(potentialOption2, "DEX", level);
                    justOptionAllStat += calItemJustStatOption(potentialOption2, "올스탯", level);

                    percentLUK += calItemPercentOption(potentialOption3,"LUK");
                    percentDEX += calItemPercentOption(potentialOption3,"DEX");
                    percentAllStat += calItemPercentOption(potentialOption3,"올스탯");
                    justOptionLUK += calItemJustStatOption(potentialOption3, "LUK", level);
                    justOptionDEX += calItemJustStatOption(potentialOption3, "DEX", level);
                    justOptionAllStat += calItemJustStatOption(potentialOption3, "올스탯", level);

                    percentLUK += calItemPercentOption(additionalPotentialOption1,"LUK");
                    percentDEX += calItemPercentOption(additionalPotentialOption1,"DEX");
                    percentAllStat += calItemPercentOption(additionalPotentialOption1,"올스탯");
                    justOptionLUK += calItemJustStatOption(additionalPotentialOption1, "LUK", level);
                    justOptionDEX += calItemJustStatOption(additionalPotentialOption1, "DEX", level);
                    justOptionAllStat += calItemJustStatOption(additionalPotentialOption1, "올스탯", level);

                    percentLUK += calItemPercentOption(additionalPotentialOption2,"LUK");
                    percentDEX += calItemPercentOption(additionalPotentialOption2,"DEX");
                    percentAllStat += calItemPercentOption(additionalPotentialOption2,"올스탯");
                    justOptionLUK += calItemJustStatOption(additionalPotentialOption2, "LUK", level);
                    justOptionDEX += calItemJustStatOption(additionalPotentialOption2, "DEX", level);
                    justOptionAllStat += calItemJustStatOption(additionalPotentialOption2, "올스탯", level);

                    percentLUK += calItemPercentOption(additionalPotentialOption3,"LUK");
                    percentDEX += calItemPercentOption(additionalPotentialOption3,"DEX");
                    percentAllStat += calItemPercentOption(additionalPotentialOption3,"올스탯");
                    justOptionLUK += calItemJustStatOption(additionalPotentialOption3, "LUK", level);
                    justOptionDEX += calItemJustStatOption(additionalPotentialOption3, "DEX", level);
                    justOptionAllStat += calItemJustStatOption(additionalPotentialOption3, "올스탯", level);
      }
        int unionAppliedLuk = calUnionStat(unionStatVo.getUnion_occupied_stat(), "LUK");
        int unionAppliedDex = calUnionStat(unionStatVo.getUnion_occupied_stat(), "DEX");
        List<UnionArtifactEffectVo> unionArtifactEffect = unionArtifactVo.getUnion_artifact_effect();
        int unionArtifactStat = 0;
        for (UnionArtifactEffectVo unionArtifactEffectVo : unionArtifactEffect) {
            String artifactName = unionArtifactEffectVo.getName();
            System.out.println("artifactName = " + artifactName);
            if(artifactName.contains("올스탯")){
                String deleteStr = artifactName.replaceAll("[^0-9]", "");
                unionArtifactStat = Integer.parseInt(deleteStr);
            }
        }

        System.out.println("unionAppliedLuk = " + unionAppliedLuk);
        System.out.println("unionAppliedDex = " + unionAppliedDex);
        System.out.println("itemLUK = " + itemLUK);
        System.out.println("itemDEX = " + itemDEX);
        System.out.println("itemAllStat = " + itemAllStat);

        System.out.println("weaponLUK = " + weaponLUK);
        System.out.println("weaponDEX = " + weaponDEX);
        System.out.println("weaponAllStat = " + weaponAllStat);

        System.out.println("percentLUK = " + percentLUK);
        System.out.println("percentDEX = " + percentDEX);
        System.out.println("percentAllStat = " + percentAllStat);

        System.out.println("justOptionLUK = " + justOptionLUK);
        System.out.println("justOptionDEX = " + justOptionDEX);
        System.out.println("justOptionAllStat = " + justOptionAllStat);

        System.out.println("unionArtifactStat = " + unionArtifactStat);
        return zipAppliedStat;
    }
    private int calItemPercentOption(String option , String stat ){
        int statPercent = 0;
        if (option == null){
            return statPercent;
        }else{
            if(option.contains("%") && option.contains(stat)){
                String deleteStr = option.replaceAll("[^0-9]", "");
                statPercent += Integer.parseInt(deleteStr);
            }
        }
        return statPercent;
    }
    private int calItemJustStatOption(String option , String stat , int level){
        int justStat = 0;
        if (option == null){
            return justStat;
        }else {
            if(!option.contains("%")&&option.contains(stat) && !option.contains("캐릭터")){
                String deleteStr = option.replaceAll("[^0-9]", "");
                justStat += Integer.parseInt(deleteStr);
            }else if (option.contains("캐릭터 기준 9레벨 당") && stat!="올스탯"){
                String deleteStr = option.replaceAll("[^0-8]", "");
                if(Integer.parseInt(deleteStr) == 1){
                    justStat = level/9;
                } else if (Integer.parseInt(deleteStr) == 2) {
                    justStat = 2 * (level/9);
                }
            }

        }
        return justStat;
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
