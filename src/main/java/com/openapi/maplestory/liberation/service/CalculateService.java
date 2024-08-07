package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.*;
import com.openapi.maplestory.liberation.domain.equipment.*;
import com.openapi.maplestory.liberation.domain.equipment.cash.CashItemBaseVo;
import com.openapi.maplestory.liberation.domain.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.cash.CashItemOptionVo;
import com.openapi.maplestory.liberation.domain.equipment.pet.ItemOption;
import com.openapi.maplestory.liberation.domain.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetEffectInfoVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetEffectVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.stat.*;
import com.openapi.maplestory.liberation.repository.dto.innerdto.AppliedStatDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.BasicInfoDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.UnAppliedStatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class CalculateService {
    private static int getPetItemPower(PetEquipmentVo petEquipmentVo, String mainPower) {
        int petItemPower = 0;
        System.out.println("petEquipmentVo = " + petEquipmentVo);
        if (petEquipmentVo.getPet_1_equipment() != null) {
            List<ItemOption> petItemOption1 = petEquipmentVo.getPet_1_equipment().getItem_option();
            for (ItemOption itemOption : petItemOption1) {
                String optionType = itemOption.getOption_type();
                String optionValue = itemOption.getOption_value();
                if (Objects.equals(optionType, mainPower)) {
                    String deleteStr = optionValue.replaceAll("[^0-9]", "");
                    petItemPower += Integer.parseInt(deleteStr);
                }
            }
        }
        if (petEquipmentVo.getPet_2_equipment() != null) {
            List<ItemOption> petItemOption2 = petEquipmentVo.getPet_2_equipment().getItem_option();
            for (ItemOption itemOption : petItemOption2) {
                String optionType = itemOption.getOption_type();
                String optionValue = itemOption.getOption_value();
                if (Objects.equals(optionType, mainPower)) {
                    String deleteStr = optionValue.replaceAll("[^0-9]", "");
                    petItemPower += Integer.parseInt(deleteStr);
                }
            }
        }
        if (petEquipmentVo.getPet_3_equipment() != null) {
            List<ItemOption> petItemOption3 = petEquipmentVo.getPet_3_equipment().getItem_option();
            for (ItemOption itemOption : petItemOption3) {
                String optionType = itemOption.getOption_type();
                String optionValue = itemOption.getOption_value();
                if (Objects.equals(optionType, mainPower)) {
                    String deleteStr = optionValue.replaceAll("[^0-9]", "");
                    petItemPower += Integer.parseInt(deleteStr);
                }
            }
        }


        return petItemPower;
    }

    public UnAppliedStatDto calUnAppliedStat(BasicInfoDto basicInfoDto, BasicVo basicVo, HyperStatVo hyperStatVo, SymbolVo symbolVo, UnionStatVo unionStatVo, HexaStatVo hexaStatVo) {
        UnAppliedStatDto unAppliedStatDto = new UnAppliedStatDto();
        String jobCase = basicInfoDto.getJob();
        String characterClass = basicVo.getCharacter_class();

        String mainStat = basicInfoDto.getMainStat();
        String subStat = basicInfoDto.getSubStat();
        String mainPower = basicInfoDto.getMainPower();
        List<String> weapon = basicInfoDto.getWeapon();

        System.out.println("mainPower = " + mainPower);
        System.out.println("subStat = " + subStat);
        System.out.println("mainStat = " + mainStat);
        System.out.println("jobCase = " + jobCase);
        System.out.println("weapon = " + weapon);

        int totalSymbol = 0;
        switch (jobCase) {
            case "럭덱":
                System.out.println("jobCase = " + jobCase);
                totalSymbol = symbolVo.getSymbol().stream()
                        .mapToInt(symbolEquipmentVo -> Integer.parseInt(symbolEquipmentVo.getSymbol_luk()))
                        .sum();
                break;
            case "힘덱":
                System.out.println("jobCase = " + jobCase);
                totalSymbol = symbolVo.getSymbol().stream()
                        .mapToInt(symbolEquipmentVo -> Integer.parseInt(symbolEquipmentVo.getSymbol_str()))
                        .sum();
                break;
            case "덱힘":
                System.out.println("jobCase = " + jobCase);
                totalSymbol = symbolVo.getSymbol().stream()
                        .mapToInt(symbolEquipmentVo -> Integer.parseInt(symbolEquipmentVo.getSymbol_dex()))
                        .sum();
                break;
            case "인럭":
                System.out.println("jobCase = " + jobCase);
                totalSymbol = symbolVo.getSymbol().stream()
                        .mapToInt(symbolEquipmentVo -> Integer.parseInt(symbolEquipmentVo.getSymbol_int()))
                        .sum();
                break;
            case "데벤제논":
                System.out.println("데벤 제논 미구현");
                break;
            default:
                System.out.println("4차 전직 이전 캐릭터 입니다! 전직부터하세요!");
                break;
        }

        String usePresetNo = hyperStatVo.getUse_preset_no();
        List<HyperStatDetailVo> hyperStatPreset = switchPreset(usePresetNo, hyperStatVo);

        int hyperMain = getHyperStat(hyperStatPreset, mainStat);
        int hyperSub = getHyperStat(hyperStatPreset, subStat);
        int hyperStrSub = 0;

        int hyperPower = getHyperStat(hyperStatPreset, "공격력/마력");
        int hyperCriDamage = getHyperStat(hyperStatPreset, "크리티컬 데미지");
        int hyperDamage = (getHyperStat(hyperStatPreset, "데미지")) + (getHyperStat(hyperStatPreset, "보스 몬스터 공격 시 데미지 증가"));

        System.out.println("hyperPower = " + hyperPower);
        System.out.println("hyperCriticalDamage = " + hyperCriDamage);
        System.out.println("hyperDamage = " + hyperDamage);

        int unionMain = calUnionStat(unionStatVo.getUnion_raider_stat(), mainStat);
        int unionSub = calUnionStat(unionStatVo.getUnion_raider_stat(), subStat);
        int unionStrSub = 0;

        int unionPower = calUnionStat(unionStatVo.getUnion_raider_stat(), "공격력/마력");
        int unionDamage = calUnionStat(unionStatVo.getUnion_raider_stat(), "보스 몬스터 공격 시 데미지");
        int unionCriDamage = calUnionStat(unionStatVo.getUnion_raider_stat(), "크리티컬 데미지");

        if(characterClass.contains("듀얼블레이더")||characterClass.contains("카데나")||characterClass.contains("섀도어")){
            hyperStrSub = getHyperStat(hyperStatPreset , "STR");
            unionStrSub = calUnionStat(unionStatVo.getUnion_raider_stat(), "STR");
        }
        System.out.println("hyperStrSub = " + hyperStrSub);
        System.out.println("unionStrSub = " + unionStrSub);

        System.out.println("unionPower = " + unionPower);
        System.out.println("unionDamage = " + unionDamage);
        System.out.println("unionCriDamage = " + unionCriDamage);


        int hexaStat = (int) filterHexaStat(hexaStatVo, "주력 스탯 증가");

        double hexaPower = filterHexaStat(hexaStatVo, mainPower + " 증가");
        double hexaDamage = (filterHexaStat(hexaStatVo, "보스 데미지 증가")) + (filterHexaStat(hexaStatVo, "데미지 증가"));
        double hexaCriDamage = filterHexaStat(hexaStatVo, "크리티컬 데미지 증가");


        System.out.println("hexaPower = " + hexaPower);
        System.out.println("hexaDamage = " + hexaDamage);
        System.out.println("hexaCriDamage = " + hexaCriDamage);

        unAppliedStatDto.setSymbol(totalSymbol);
        unAppliedStatDto.setMainStat(hyperMain + unionMain + hexaStat);
        unAppliedStatDto.setSubStat(hyperSub + unionSub);

        unAppliedStatDto.setPower(hyperPower + unionPower + hexaPower);
        unAppliedStatDto.setCriDamage(hyperCriDamage + unionCriDamage + hexaCriDamage);
        unAppliedStatDto.setDamage(hyperDamage + unionDamage + hexaDamage);

        unAppliedStatDto.setStrSubStat(hyperStrSub + unionStrSub);

        System.out.println("hexaStat = " + hexaStat);
        System.out.println("unionMain = " + unionMain);
        System.out.println("hyperMain = " + hyperMain);
        System.out.println("totalSymbol = " + totalSymbol);

        return unAppliedStatDto;
    }

    public AppliedStatDto calAppliedStat(BasicInfoDto basicInfoDto, BasicVo basic, StatVo stat, ItemVo itemVo, CashItemEquipmentVo cashItemEquipmentVo, AbilityVo abilityVo, SetVo setVo, CharacterSkillVo skillVo, String date, UnionStatVo unionStatVo, UnionArtifactVo unionArtifactVo, PetEquipmentVo petEquipmentVo) throws ParseException {
        AppliedStatDto appliedStatDto = new AppliedStatDto();
        List<Integer> zipAppliedStat = new ArrayList<>();
        //일단 주스탯 순수 스탯구하기
        int level = basic.getCharacter_level();
        String characterClass = basic.getCharacter_class();
        int pureStat = level * 5 + 18;
        System.out.println("pureStat = " + pureStat);
        String mainStat = basicInfoDto.getMainStat();
        String subStat = basicInfoDto.getSubStat();
        String mainPower = basicInfoDto.getMainPower();
        String jobCase = basicInfoDto.getJob();
        List<String> weapon = basicInfoDto.getWeapon();
        double criDamage = 0.0;
        double damage = 0.0;
        //캐시장비
        List<CashItemBaseVo> cashItemEquipmentBase = cashItemEquipmentVo.getCash_item_equipment_base();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate1 = format.parse(date);
        int cashItemMainStat = 0;
        int cashItemSubStat = 0;
        int cashItemStrSubStat = 0;
        int cashItemMainPower = 0;


        for (CashItemBaseVo cashItemBaseVo : cashItemEquipmentBase) {
            System.out.println("cashItemBaseVo = " + cashItemBaseVo.getCashItemOptionVo());
            List<CashItemOptionVo> cashItemOptionVo = cashItemBaseVo.getCashItemOptionVo();
            for (CashItemOptionVo itemOptionVo : cashItemOptionVo) {
                String optionType = itemOptionVo.getOption_type();
                int optionValue = Integer.parseInt(itemOptionVo.getOption_value());
                if (Objects.equals(optionType, mainStat)) {
                    cashItemMainStat += optionValue;
                } else if (Objects.equals(optionType, subStat)) {
                    cashItemSubStat += optionValue;
                } else if (Objects.equals(optionType, "STR")) {
                    cashItemStrSubStat += optionValue;
                } else if (Objects.equals(optionType, mainPower)) {
                    cashItemMainPower += optionValue;
                }
            }
        }
        System.out.println("cashItemMain= " + cashItemMainStat);
        System.out.println("cashItemMainPower = " + cashItemMainPower);
        System.out.println("cashItemSubStat = " + cashItemSubStat);
        System.out.println("cashItemStrSubStat = " + cashItemStrSubStat);

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
        int calSetMainPower = 0;
        int calSetSubStat = 0;
        int calSetStrSubStat = 0;
        double calSetDamage = 0.0;
        double calSetCriDamage = 0.0;

        List<SetEffectVo> setEffect = setVo.getSet_effect();
        for (SetEffectVo setEffectVo : setEffect) {
            System.out.println("setEffectVo = " + setEffectVo);
            List<SetEffectInfoVo> setEffectInfoVo = setEffectVo.getSetEffectInfoVo();
            for (SetEffectInfoVo effectInfoVo : setEffectInfoVo) {
                String setOption = effectInfoVo.getSet_option();
                String[] splitSetOption = setOption.split(",");
                for (String option : splitSetOption) {
                    if (option.contains("올스탯")) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetStat += Integer.parseInt(deleteStr);
                        calSetSubStat += Integer.parseInt(deleteStr);
                        calSetStrSubStat += Integer.parseInt(deleteStr);
                    } else if (option.contains(mainStat)) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetStat += Integer.parseInt(deleteStr);
                    } else if (option.contains(subStat)) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetSubStat += Integer.parseInt(deleteStr);
                    } else if (option.contains("STR")) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetStrSubStat += Integer.parseInt(deleteStr);
                    } else if (option.contains(mainPower)) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetMainPower += Integer.parseInt(deleteStr);
                    } else if (option.contains("보스 몬스터 공격 시 데미지")) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetDamage += Integer.parseInt(deleteStr);
                    } else if (option.contains("크리티컬 데미지")) {
                        String deleteStr = option.replaceAll("[^0-9]", "");
                        calSetCriDamage += Integer.parseInt(deleteStr);
                    }
                }
            }
        }

        System.out.println("calSetStat = " + calSetStat);
        System.out.println("calSetMainPower = " + calSetMainPower);
        System.out.println("calSetSubStat = " + calSetSubStat);
        System.out.println("calSetDamage = " + calSetDamage);
        System.out.println("calSetCriDamage = " + calSetCriDamage);
        System.out.println("calSetStrSubStat = " + calSetStrSubStat);


        int titleStat = 0;
        int titlePower = 0;
        double titleDamage = 0.0;
        Title title = itemVo.getTitle();
        Date titleDate;
        if (title != null) {
            String[] splitTitle = title.getTitle_description().split("\n");
            System.out.println("splitTitle = " + splitTitle.toString());
            for (String option : splitTitle) {
                String[] split = option.split(",");
                for (String s : split) {
                    System.out.println("s = " + s);
                    if (title != null && title.getDate_option_expire() == null) {
                        if (s.contains("올스탯")) {
                            String deleteStr = s.replaceAll("[^0-9]", "");
                            titleStat += Integer.parseInt(deleteStr);
                        } else if (s.contains(mainPower)) {
                            String deleteStr = s.replaceAll("[^0-9]", "");
                            titlePower += Integer.parseInt(deleteStr);
                        } else if (s.contains("보스 몬스터 공격 시 데미지")) {
                            String deleteStr = s.replaceAll("[^0-9]", "");
                            titleDamage += Double.parseDouble(deleteStr);
                        }

                    } else if (title != null) {
                        String dateOptionExpire = title.getDate_option_expire();
                        if (!dateOptionExpire.contains("expired")) {
                            if (s.contains("올스탯")) {
                                String deleteStr = s.replaceAll("[^0-9]", "");
                                titleStat += Integer.parseInt(deleteStr);
                            } else if (s.contains(mainPower)) {
                                String deleteStr = s.replaceAll("[^0-9]", "");
                                titlePower += Integer.parseInt(deleteStr);
                            } else if (s.contains("보스 몬스터 공격 시 데미지")) {
                                String deleteStr = s.replaceAll("[^0-9]", "");
                                titleDamage += Double.parseDouble(deleteStr);
                            }

                        }
                    }
                }
            }
        }

        System.out.println("titleStat = " + titleStat);
        System.out.println("titlePower = " + titlePower);
        System.out.println("titleDamage = " + titleDamage);


        int itemMainStat = 0;
        int itemSubStat = 0;
        int itemAllStat = 0;
        int weaponMainStat = 0;
        int weaponSubStat = 0;
        int weaponAllStat = 0;
        int percentMainStat = 0;
        int justOptionMainStat = 0;
        int percentSubStat = 0;
        int justOptionSubStat = 0;
        int percentAllStat = 0;
        int justOptionAllStat = 0;
        int itemMainPower = 0;
        int percentMainPower = 0;
        int justOptionMainPower = 0;
        int itemDamage = 0;
        int itemCriDamage = 0;

        int zeroSubWeaponStr = 0;
        int zeroSubWeaponDex = 0;
        int zeroSubWeaponPower = 0;
        double zeroSubWeaponDamage = 0.0;

        int itemStrSubStat = 0;
        int percentStrSubStat = 0;
        int justOptionStrSubStat = 0;


        List<ItemEquipmentVo> itemEquipment = itemVo.getItem_equipment();
        for (ItemEquipmentVo itemEquipmentVo : itemEquipment) {
            ItemTotalOptionVo itemTotalOption = itemEquipmentVo.getItemTotalOption();
            ItemExceptionalOptionVo itemExceptionalOption = itemEquipmentVo.getItem_exceptional_option();
            int totMainStat = 0;
            int totSubStat = 0;
            int totMainPower = 0;

            int totStrSubStat = 0;
            if(characterClass.contains("듀얼블레이더")||characterClass.contains("카데나")||characterClass.contains("섀도어")){
                totStrSubStat = Integer.parseInt(itemTotalOption.getStr());
                System.out.println("totStrSubStat = " + totStrSubStat);
            }
            switch (jobCase) {
                case "럭덱":
                    totMainStat = Integer.parseInt(itemTotalOption.getLuk()) + Integer.parseInt(itemExceptionalOption.getLuk());
                    totSubStat = Integer.parseInt(itemTotalOption.getDex()) + Integer.parseInt(itemExceptionalOption.getDex());
                    totMainPower = Integer.parseInt(itemTotalOption.getAttack_power()) + Integer.parseInt(itemExceptionalOption.getAttack_power());
                    System.out.println("아이템 = " + itemEquipmentVo.getItem_name() + "공격력 = " + totMainPower);
                    break;
                case "힘덱":
                    totMainStat = Integer.parseInt(itemTotalOption.getStr()) + Integer.parseInt(itemExceptionalOption.getStr());
                    totSubStat = Integer.parseInt(itemTotalOption.getDex()) + Integer.parseInt(itemExceptionalOption.getDex());
                    totMainPower = Integer.parseInt(itemTotalOption.getAttack_power()) + Integer.parseInt(itemExceptionalOption.getAttack_power());
                    break;
                case "덱힘":
                    totMainStat = Integer.parseInt(itemTotalOption.getDex()) + Integer.parseInt(itemExceptionalOption.getDex());
                    totSubStat = Integer.parseInt(itemTotalOption.getStr()) + Integer.parseInt(itemExceptionalOption.getStr());
                    totMainPower = Integer.parseInt(itemTotalOption.getAttack_power()) + Integer.parseInt(itemExceptionalOption.getAttack_power());
                    break;
                case "인럭":
                    totMainStat = Integer.parseInt(itemTotalOption.getInt()) + Integer.parseInt(itemExceptionalOption.getInt());
                    totSubStat = Integer.parseInt(itemTotalOption.getLuk()) + Integer.parseInt(itemExceptionalOption.getLuk());
                    totMainPower = Integer.parseInt(itemTotalOption.getMagic_power()) + Integer.parseInt(itemExceptionalOption.getMagic_power());
                    break;
                case "데벤제논":
                    System.out.println("데벤 제논 미구현");
                    break;
                default:
                    System.out.println("4차 전직 이전 캐릭터 입니다! 전직부터하세요!");
                    mainStat = "";
                    subStat = "";
                    break;
            }

            String itemEquipmentPart = itemEquipmentVo.getItem_equipment_part();
            String potentialOption1 = itemEquipmentVo.getPotential_option_1();
            String potentialOption2 = itemEquipmentVo.getPotential_option_2();
            String potentialOption3 = itemEquipmentVo.getPotential_option_3();
            String additionalPotentialOption1 = itemEquipmentVo.getAdditional_potential_option_1();
            String additionalPotentialOption2 = itemEquipmentVo.getAdditional_potential_option_2();
            String additionalPotentialOption3 = itemEquipmentVo.getAdditional_potential_option_3();
            Object[] potentialOption = {potentialOption1, potentialOption2, potentialOption3};
            Object[] additionalPotentialOption = {additionalPotentialOption1, additionalPotentialOption2, additionalPotentialOption3};

            String soulOption = itemEquipmentVo.getSoul_option();
            String totAllStat = itemTotalOption.getAll_stat();
            System.out.println("itemExceptionalOption = " + itemExceptionalOption);
            boolean weaponBol = false;
            for (String s : weapon) {
                if (s.contains(itemEquipmentPart)) {
                    System.out.println("itemTotalOption weapon = " + itemTotalOption);
                    weaponBol = true;
                }
            }
            System.out.println("itemEquipmentPart = " + itemEquipmentPart);
            String itemName = itemEquipmentVo.getItem_name();
            if (itemName.contains("딥다크")){
                itemCriDamage += 5;
            }

            if(itemEquipmentPart.contains("대검")){
                ItemAddOptionVo itemAddOption = itemEquipmentVo.getItem_add_option();
                zeroSubWeaponStr = Integer.parseInt(itemTotalOption.getStr()) + 1;
                zeroSubWeaponDex = Integer.parseInt(itemTotalOption.getDex());
                zeroSubWeaponPower = Integer.parseInt(itemTotalOption.getAttack_power()) - Integer.parseInt(itemAddOption.getAttack_power()) + 1;
                zeroSubWeaponDamage = Integer.parseInt(itemTotalOption.getBoss_damage()) - Integer.parseInt(itemAddOption.getBoss_damage());
            }
            itemMainStat += totMainStat;
            itemSubStat += totSubStat;
            itemAllStat += Integer.parseInt(totAllStat);
            itemMainPower += totMainPower;
            itemDamage += Integer.parseInt(itemTotalOption.getDamage()) + Integer.parseInt(itemTotalOption.getBoss_damage());

            itemStrSubStat += totStrSubStat;

            if (soulOption != null) {
                System.out.println("soulOption = " + soulOption);
                if (soulOption.contains(mainPower) && soulOption.contains("%")) {
                    String deleteStr = soulOption.replaceAll("[^0-9]", "");
                    percentMainPower += Integer.parseInt(deleteStr);
                } else if (soulOption.contains(mainPower) && !soulOption.contains("%")) {
                    String deleteStr = soulOption.replaceAll("[^0-9]", "");
                    justOptionMainPower += Integer.parseInt(deleteStr);
                } else if (soulOption.contains("올스탯") && soulOption.contains("%")) {
                    String deleteStr = soulOption.replaceAll("[^0-9]", "");
                    percentAllStat += Integer.parseInt(deleteStr);
                } else if (soulOption.contains("올스탯") && !soulOption.contains("%")) {
                    String deleteStr = soulOption.replaceAll("[^0-9]", "");
                    justOptionAllStat += Integer.parseInt(deleteStr);
                } else if (soulOption.contains("보스")) {
                    String deleteStr = soulOption.replaceAll("[^0-9]", "");
                    itemDamage += Integer.parseInt(deleteStr);
                }
            }
            if (totStrSubStat != 0){
                for (Object option : potentialOption) {
                    percentStrSubStat += calItemPercentOption((String) option, "STR");
                    justOptionStrSubStat += calItemJustStatOption((String) option, "STR", level);
                }
                for (Object additionalOption : additionalPotentialOption) {
                    percentStrSubStat += calItemPercentOption((String) additionalOption, "STR");
                    justOptionStrSubStat += calItemJustStatOption((String) additionalOption, "STR", level);
                  }
            }
            for (Object option : potentialOption) {
                percentMainStat += calItemPercentOption((String) option, mainStat);
                percentSubStat += calItemPercentOption((String) option, subStat);
                percentAllStat += calItemPercentOption((String) option, "올스탯");
                percentMainPower += calItemPercentOption((String) option, mainPower);
                itemDamage += calItemPercentOption((String) option, "데미지");
                itemCriDamage += calItemPercentOption((String) option, "크리티컬 데미지");

                justOptionMainStat += calItemJustStatOption((String) option, mainStat, level);
                justOptionSubStat += calItemJustStatOption((String) option, subStat, level);
                justOptionAllStat += calItemJustStatOption((String) option, "올스탯", level);
                justOptionMainPower += calItemJustStatOption((String) option, mainPower, level);
            }
            for (Object additionalOption : additionalPotentialOption) {
                percentMainStat += calItemPercentOption((String) additionalOption, mainStat);
                percentSubStat += calItemPercentOption((String) additionalOption, subStat);
                percentAllStat += calItemPercentOption((String) additionalOption, "올스탯");
                percentMainPower += calItemPercentOption((String) additionalOption, mainPower);
                itemDamage += calItemPercentOption((String) additionalOption, "데미지");
                itemCriDamage += calItemPercentOption((String) additionalOption, "크리티컬 데미지");


                justOptionMainStat += calItemJustStatOption((String) additionalOption, mainStat, level);
                justOptionSubStat += calItemJustStatOption((String) additionalOption, subStat, level);
                justOptionAllStat += calItemJustStatOption((String) additionalOption, "올스탯", level);
                justOptionMainPower += calItemJustStatOption((String) additionalOption, mainPower, level);
            }


        }
        int unionAppliedMainStat = calUnionStat(unionStatVo.getUnion_occupied_stat(), mainStat);
        int unionAppliedSubStat = calUnionStat(unionStatVo.getUnion_occupied_stat(), subStat);
        int unionAppliedStrSubStat = calUnionStat(unionStatVo.getUnion_occupied_stat(), "STR");

        double unionAppliedPower = calUnionOccupiedStat(unionStatVo.getUnion_occupied_stat(), mainPower);
        double unionAppliedDamage = calUnionOccupiedStat(unionStatVo.getUnion_occupied_stat(), "보스 몬스터 공격 시 데미지");
        double unionAppliedCriDamage = calUnionOccupiedStat(unionStatVo.getUnion_occupied_stat(), "크리티컬 데미지");
        unionAppliedCriDamage = unionAppliedCriDamage / 100;
        System.out.println("unionAppliedPower = " + unionAppliedPower);
        System.out.println("unionAppliedCriDamage = " + unionAppliedCriDamage);
        System.out.println("unionAppliedDamage = " + unionAppliedDamage);

        List<UnionArtifactEffectVo> unionArtifactEffect = unionArtifactVo.getUnion_artifact_effect();
        int unionArtifactStat = 0;
        int unionArtifactPower = 0;
        double unionArtifactDamage = 0;
        double unionArtifactCriDamage = 0;
        double unionArtifactBossDamage = 0;
        for (UnionArtifactEffectVo unionArtifactEffectVo : unionArtifactEffect) {
            String artifactName = unionArtifactEffectVo.getName();
            if (artifactName.contains("올스탯")) {
                String deleteStr = artifactName.replaceAll("[^0-9]", "");
                unionArtifactStat = Integer.parseInt(deleteStr);
            } else if (artifactName.contains(mainPower)) {
                String[] split = artifactName.split(",");
                for (String s : split) {
                    if (s.contains(mainPower)) {
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        unionArtifactPower = Integer.parseInt(deleteStr);
                    }
                }
            } else if (artifactName.contains("보스 몬스터 공격 시 데미지")) {
                System.out.println("artifactNameBossDamage = " + artifactName);
                String deleteStr = artifactName.replaceAll("[^0-9]", "");
                unionArtifactBossDamage = Integer.parseInt(deleteStr) / 100;
            } else if (artifactName.contains("크리티컬 데미지")) {
                System.out.println("artifactNameCriDamage = " + artifactName);
                String deleteStr = artifactName.replaceAll("[^0-9]", "");
                unionArtifactCriDamage = Integer.parseInt(deleteStr) / 100;
            } else if (artifactName.contains("데미지")) {
                System.out.println("artifactNameDamage = " + artifactName);
                String deleteStr = artifactName.replaceAll("[^0-9]", "");
                unionArtifactDamage = Integer.parseInt(deleteStr) / 100;
            }
        }

        System.out.println("unionArtifactBossDamage = " + unionArtifactBossDamage);
        System.out.println("unionArtifactPower = " + unionArtifactPower);
        System.out.println("unionArtifactCriDamage = " + unionArtifactCriDamage);
        System.out.println("unionArtifactDamage = " + unionArtifactDamage);

        int abilityPower = 0;
        int abilityDamage = 0;
        List<AbilityInfo> abilityInfo = abilityVo.getAbility_info();
        for (AbilityInfo info : abilityInfo) {
            System.out.println("info = " + info);
            String abilityValue = info.getAbility_value();
            if (abilityValue.contains(mainPower)) {
                String deleteStr = abilityValue.replaceAll("[^0-9]", "");
                abilityPower += Integer.parseInt(deleteStr);
            } else if (abilityValue.contains("보스 몬스터 공격")) {
                String deleteStr = abilityValue.replaceAll("[^0-9]", "");
                abilityDamage += Integer.parseInt(deleteStr);
            }
        }
        System.out.println("abilityPower = " + abilityPower);
        System.out.println("abilityDamage = " + abilityDamage);

        int petItemPower = getPetItemPower(petEquipmentVo, mainPower);
        System.out.println("petItemPower = " + petItemPower);

        int petSetPower = 0;
        String blessEffect = "";
        int blessPowerA = 0;
        int blessPowerB = 0;

        int mayakPower = 0;
        double mayakDamage = 0.0;
        int mayakAllStat = 0;

        int rebootPower = 0;
        double rebootLastDamage = 0.0;

        List<SkillVo> skillVo1 = skillVo.getSkillVo();
        for (SkillVo vo : skillVo1) {
            String skillName = vo.getSkill_name();
            System.out.println("vo = " + vo);
            /*
            * 마약 스킬 변경 때마다 변경해줘야함
            * 아 근데 이거 어떻게 자동화 할수 있는 로직이 없을까
            * */
            if (skillName.contains("리부트")){
                rebootPower = 5;
                String skillEffect = vo.getSkill_effect();
                System.out.println("skillEffect = " + skillEffect);
                if(level > 199 && level < 250){
                    rebootLastDamage = 1.35;
                } else if (level > 249) {
                    rebootLastDamage = 1.45;
                }
            }
            if(skillName.contains("아스완")){
                String skillEffect = vo.getSkill_effect();
                String[] split = skillEffect.split("증가");
                for (String s : split) {
                    if (s.contains("보스 몬스터 공격")){
                        System.out.println("s = " + s);
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        mayakDamage += Integer.parseInt(deleteStr);
                    } else if (s.contains(mainPower)){
                        System.out.println("s = " + s);
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        mayakPower += Integer.parseInt(deleteStr);
                    } else if (s.contains("올스탯")){
                        System.out.println("s = " + s);
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        mayakAllStat += Integer.parseInt(deleteStr);
                    }
                }
            }
            if (skillName.contains("정령의 축복")) {
                blessEffect = vo.getSkill_effect();
                String[] split = blessEffect.split(",");
                for (String s : split) {
                    if (s.contains(mainPower)) {
                        System.out.println("s = " + s);
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        blessPowerA += Integer.parseInt(deleteStr);
                    }
                }
            } else if (skillName.contains("여제의 축복")) {
                blessEffect = vo.getSkill_effect();
                String[] split = blessEffect.split(",");
                for (String s : split) {
                    if (s.contains(mainPower)) {
                        System.out.println("s = " + s);
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        blessPowerB += Integer.parseInt(deleteStr);
                    }
                }
            }

            if (skillName.contains("Lv")) {
                String skillEffect = vo.getSkill_effect();
                String[] split = skillEffect.split(",");
                System.out.println("skillEffect = " + skillEffect);
                for (String s : split) {
                    System.out.println("s = " + s);
                    if (s.contains(mainPower)) {
                        String deleteStr = s.replaceAll("[^0-9]", "");
                        petSetPower += Integer.parseInt(deleteStr);
                    }
                }
            }
        }
        System.out.println("mayakAllStat = " + mayakAllStat);
        System.out.println("mayakDamage = " + mayakDamage);
        System.out.println("mayakPower = " + mayakPower);
        System.out.println("rebootPower = " + rebootPower);
        System.out.println("rebootLastDamage = " + rebootLastDamage);
        int finalBlessPower = 0;
        if (blessPowerA >= blessPowerB) {
            finalBlessPower = blessPowerA;
        } else if (blessPowerB >= blessPowerA) {
            finalBlessPower = blessPowerB;
        }

        System.out.println("finalBlessPower = " + finalBlessPower);
        System.out.println("petSetPower = " + petSetPower);
        System.out.println("itemDamage = " + itemDamage);
        itemDamage = itemDamage - itemCriDamage;


        System.out.println("unionAppliedMainStat = " + unionAppliedMainStat);
        System.out.println("unionAppliedSubStat = " + unionAppliedSubStat);

        System.out.println("itemMainStat= " + itemMainStat);
        System.out.println("itemSubStat = " + itemSubStat);
        System.out.println("itemAllStat = " + itemAllStat);
        System.out.println("itemMainPower = " + itemMainPower);
        System.out.println("itemDamage = " + itemDamage);
        System.out.println("itemCriDamage = " + itemCriDamage);

        System.out.println("weaponMainStat = " + weaponMainStat);
        System.out.println("weaponSunStat = " + weaponSubStat);
        System.out.println("weaponAllStat = " + weaponAllStat);

        System.out.println("percentMainStat = " + percentMainStat);
        System.out.println("percentSubStat = " + percentSubStat);
        System.out.println("percentAllStat = " + percentAllStat);
        System.out.println("percentMainPower = " + percentMainPower);

        System.out.println("justOptionMainStat = " + justOptionMainStat);
        System.out.println("justOptionSubStat = " + justOptionSubStat);
        System.out.println("justOptionAllStat = " + justOptionAllStat);
        System.out.println("justOptionMainPower = " + justOptionMainPower);

        System.out.println("unionArtifactStat = " + unionArtifactStat);


        int lastMainStat = pureStat + cashItemMainStat + calSetStat + titleStat + unionAppliedMainStat + unionArtifactStat + itemMainStat + justOptionMainStat + justOptionAllStat + mayakAllStat - zeroSubWeaponStr;
        int lastSubStat = 4 + cashItemSubStat + calSetSubStat + titleStat + unionAppliedSubStat + unionArtifactStat + itemSubStat + justOptionSubStat + justOptionAllStat + mayakAllStat - zeroSubWeaponDex;

        int lastStrSubStat = 0;
        int lastPercentStrSubStat = 0;

        double lastMainPower = 0;
        if (petSetPower != 0) {
            lastMainPower = cashItemMainPower + calSetMainPower + titlePower + unionAppliedPower + unionArtifactPower + finalBlessPower + petSetPower + itemMainPower + justOptionMainPower + petItemPower + abilityPower + mayakPower - zeroSubWeaponPower + rebootPower;
        } else {
            lastMainPower = cashItemMainPower + calSetMainPower + titlePower + unionAppliedPower + unionArtifactPower + finalBlessPower + petSetPower + itemMainPower + justOptionMainPower + abilityPower + mayakPower - zeroSubWeaponPower + rebootPower;
        }
        int lastPercentMainStat = percentMainStat + percentAllStat + itemAllStat;
        int lastPercentSubStat = percentSubStat + percentAllStat + itemAllStat;
        int lastPercentMainPower = percentMainPower;

        double lastDamage = calSetDamage + titleDamage + unionAppliedDamage + unionArtifactDamage + unionArtifactBossDamage + itemDamage + abilityDamage + mayakDamage - zeroSubWeaponDamage;
        double lastCriDamage = calSetCriDamage + unionAppliedCriDamage + unionArtifactCriDamage + itemCriDamage;

        if(characterClass.contains("듀얼블레이더")||characterClass.contains("카데나")||characterClass.contains("섀도어")){
            lastStrSubStat = 4 +cashItemStrSubStat + calSetStrSubStat + calSetStrSubStat + titleStat + unionAppliedStrSubStat + unionArtifactStat + itemStrSubStat + justOptionStrSubStat + justOptionAllStat + mayakAllStat;
            lastPercentStrSubStat = percentStrSubStat + percentAllStat + itemAllStat;
        }

        System.out.println("lastPercentSubStat = " + lastPercentSubStat);
        System.out.println("lastStrSubStat = " + lastStrSubStat);
        System.out.println("itemStrSubStat = " + itemStrSubStat);
        System.out.println("lastPercentStrSubStat = " + lastPercentStrSubStat);

        appliedStatDto.setMainStat(lastMainStat);
        appliedStatDto.setSubStat(lastSubStat);
        appliedStatDto.setPower((int) lastMainPower);

        appliedStatDto.setMainStatPercent(lastPercentMainStat);
        appliedStatDto.setSubStatPercent(lastPercentSubStat);
        appliedStatDto.setPowerPercent(lastPercentMainPower);

        appliedStatDto.setDamage(lastDamage);
        appliedStatDto.setCriDamage(lastCriDamage);
        appliedStatDto.setRebootLastDamage(rebootLastDamage);

        appliedStatDto.setSubStrStat(lastStrSubStat);
        appliedStatDto.setSubStrStatPercent(lastPercentStrSubStat);

        System.out.println("lastMainStat = " + lastMainStat);
        System.out.println("lastSubStat = " + lastSubStat);
        System.out.println("lastMainPower = " + lastMainPower);
        System.out.println("lastPercentMainStat = " + lastPercentMainStat);
        System.out.println("lastPercentSubStat = " + lastPercentSubStat);
        System.out.println("lastPercentMainPower = " + lastPercentMainPower);
        System.out.println("lastDamage = " + lastDamage);
        System.out.println("lastCriDamage = " + lastCriDamage);

        return appliedStatDto;
    }

    private int calItemPercentOption(String option, String stat) {
        int statPercent = 0;
        if (option == null) {
            return statPercent;
        } else {
            if (option.contains("%") && option.contains(stat) && !option.contains("피격")) {
                String deleteStr = option.replaceAll("[^0-9]", "");
                statPercent += Integer.parseInt(deleteStr);
            }
        }
        return statPercent;
    }

    private int calItemJustStatOption(String option, String stat, int level) {
        int justStat = 0;
        if (option == null) {
            return justStat;
        } else {
            if (!option.contains("%") && option.contains(stat) && !option.contains("캐릭터")) {
                String deleteStr = option.replaceAll("[^0-9]", "");
                justStat += Integer.parseInt(deleteStr);
            } else if (option.contains("캐릭터 기준 9레벨 당") && stat != "올스탯" && option.contains(stat)) {
                String deleteStr = option.replaceAll("[^0-8]", "");
                if (Integer.parseInt(deleteStr) == 1) {
                    justStat = level / 9;
                } else if (Integer.parseInt(deleteStr) == 2) {
                    justStat = 2 * (level / 9);
                }
            }

        }
        return justStat;
    }

    private List<HyperStatDetailVo> switchPreset(String usePresetNo, HyperStatVo hyperStatVo) {
        switch (usePresetNo) {
            case "1":
                return hyperStatVo.getHyper_stat_preset_1();
            case "2":
                return hyperStatVo.getHyper_stat_preset_2();
            default:
                return hyperStatVo.getHyper_stat_preset_3();
        }
    }

    private int getHyperStat(List<HyperStatDetailVo> hyperStatPreset, String statType) {
        return hyperStatPreset.stream()
                .filter(detailVo -> Objects.equals(detailVo.getStat_type(), statType) && detailVo.getStat_increase() != null)
                .mapToInt(detailVo -> Integer.parseInt(detailVo.getStat_increase().replaceAll("[^0-9]", "")))
                .findFirst()
                .orElse(0);
    }

    private int calUnionStat(List<String> statList, String statType) {
        return statList.stream()
                .filter(statValue -> statValue.contains(statType))
                .mapToInt(statValue -> Integer.parseInt(statValue.replaceAll("[^0-9]", "")))
                .sum();
    }

    private double calUnionOccupiedStat(List<String> statList, String statType) {
        return statList.stream()
                .filter(statValue -> statValue.contains(statType))
                .mapToDouble(statValue -> Integer.parseInt(statValue.replaceAll("[^0-9]", "")))
                .sum();
    }

    public double filterHexaStat(HexaStatVo hexaStatVo, String statType) {
        double result = 0;
        int x = 0;
        boolean isMainStat = false;
        List<HexaStatDetailVo> hexaStatDetailVo = hexaStatVo.getHexaStatDetailVo();
        for (HexaStatDetailVo statDetailVo : hexaStatDetailVo) {
            System.out.println("statDetailVo = " + statDetailVo);
            if (Objects.equals(statDetailVo.getMain_stat_name(), statType) && statDetailVo.getMain_stat_level() != 0) {
                isMainStat = true;
                x = (statDetailVo.getMain_stat_level()) - 1;
            } else if (Objects.equals(statDetailVo.getSub_stat_name_1(), statType) && statDetailVo.getSub_stat_level_1() != 0) {
                isMainStat = false;
                x = (statDetailVo.getSub_stat_level_1()) - 1;
            } else if (Objects.equals(statDetailVo.getSub_stat_name_2(), statType) && statDetailVo.getSub_stat_level_2() != 0) {
                isMainStat = false;
                x = (statDetailVo.getSub_stat_level_2()) - 1;
            } else {
                return result;
            }
        }
        double[] multipliers = new double[0];
        if (x < 0) {
            return result;
        } else if (x > 0) {
            System.out.println("multipliers = " + multipliers);
            multipliers = getHexaStat(statType, isMainStat);
            result = multipliers[x];
        }
        return result;
    }

    private double[] getHexaStat(String statType, boolean isMainStat) {
        if (isMainStat) {
            switch (statType) {
                case "크리티컬 데미지 증가":
                    return new double[]{0.35, 0.7, 1.05, 1.4, 2.10, 2.8, 3.5, 4.55, 5.6, 7.0};
                case "보스 데미지 증가":
                    return new double[]{1, 2, 3, 4, 6, 8, 10, 13, 16, 20};
                case "데미지 증가":
                    return new double[]{0.75, 1.5, 2.25, 3, 4.5, 6, 7.5, 9.75, 12, 15};
                case "공격력 증가", "마력 증가":
                    return new double[]{5, 10, 15, 20, 30, 40, 50, 65, 80, 100};
                case "주력 스탯 증가":
                    return new double[]{100, 200, 300, 400, 600, 800, 1000, 1300, 1600, 2000};
            }

        } else {
            switch (statType) {
                case "크리티컬 데미지 증가":
                    return new double[]{0.35, 0.75, 1.05, 1.4, 1.75, 2.1, 2.45, 2.8, 3.15, 3.5};
                case "보스 데미지 증가":
                    return new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                case "데미지 증가":
                    return new double[]{0.75, 1.5, 2.25, 3.0, 3.75, 4.5, 5.25, 6.0, 6.75, 7.5};
                case "공격력 증가", "마력 증가":
                    return new double[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
                case "주력 스탯 증가":
                    return new double[]{100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
            }
        }
        return new double[0];
    }
}

