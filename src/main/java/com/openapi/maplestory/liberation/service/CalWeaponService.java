package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.BasicVo;
import com.openapi.maplestory.liberation.domain.equipment.*;
import com.openapi.maplestory.liberation.repository.dto.innerdto.BasicInfoDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.WeaponStatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class CalWeaponService {
    public WeaponStatDto calWeapon(BasicInfoDto basicInfoDto, ItemVo itemVo, BasicVo basicVo) {
        WeaponStatDto weaponStatDto = new WeaponStatDto();
        int level = basicVo.getCharacter_level();
        String jobCase = basicInfoDto.getJob();
        List<String> weapon = basicInfoDto.getWeapon();
        String mainStat = basicInfoDto.getMainStat();
        String subStat = basicInfoDto.getSubStat();
        String mainPower = basicInfoDto.getMainPower();

        int itemMainStat = 0;
        int itemSubStat = 0;
        int itemAllStat = 0;
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
        int itemOptionDamage = 0;
        int itemCriDamage = 0;

        int addOptionMainPower = 0;
        int baseOptionMainPower = 0;

        int etcPower = 0;
        int starForcePower = 0;

        String weaponName = "";
        String weaponPart = "";

        int scrollUpgrade = 0;
        int starForce = 0;

        List<ItemEquipmentVo> itemEquipment = itemVo.getItem_equipment();

        for (ItemEquipmentVo itemEquipmentVo : itemEquipment) {
            ItemTotalOptionVo itemTotalOption = itemEquipmentVo.getItemTotalOption();
            ItemExceptionalOptionVo itemExceptionalOption = itemEquipmentVo.getItem_exceptional_option();
            ItemAddOptionVo itemAddOption = itemEquipmentVo.getItem_add_option();
            ItemBaseOptionVo itemBaseOption = itemEquipmentVo.getItem_base_option();
            int totMainStat = 0;
            int totSubStat = 0;
            int totMainPower = 0;
            String itemEquipmentPart = itemEquipmentVo.getItem_equipment_part();
            String itemName = itemEquipmentVo.getItem_name();
            for (String s : weapon) {
                if (Objects.equals(itemEquipmentPart, s)) {
                    weaponPart = itemEquipmentPart;
                    ItemEtcOptionVo itemEtcOption = itemEquipmentVo.getItem_etc_option();
                    ItemStarforceOptionVo itemStarforceOption = itemEquipmentVo.getItem_starforce_option();
                    scrollUpgrade = Integer.parseInt(itemEquipmentVo.getScroll_upgrade());
                    starForce = Integer.parseInt(itemEquipmentVo.getStarforce());

                    weaponName = itemName;
                    System.out.println("weaponName = " + weaponName);
                    weaponStatDto.setWeaponName(itemName);
                    switch (jobCase) {
                        case "럭덱":
                            totMainStat = Integer.parseInt(itemTotalOption.getLuk()) + Integer.parseInt(itemExceptionalOption.getLuk());
                            totSubStat = Integer.parseInt(itemTotalOption.getDex()) + Integer.parseInt(itemExceptionalOption.getDex());
                            totMainPower = Integer.parseInt(itemTotalOption.getAttack_power()) + Integer.parseInt(itemExceptionalOption.getAttack_power());
                            addOptionMainPower = Integer.parseInt(itemAddOption.getAttack_power());
                            baseOptionMainPower = Integer.parseInt(itemBaseOption.getAttack_power());
                            etcPower = Integer.parseInt(itemEtcOption.getAttack_power());
                            starForcePower = Integer.parseInt(itemStarforceOption.getAttack_power());
                            break;
                        case "힘덱":
                            totMainStat = Integer.parseInt(itemTotalOption.getStr()) + Integer.parseInt(itemExceptionalOption.getStr());
                            totSubStat = Integer.parseInt(itemTotalOption.getDex()) + Integer.parseInt(itemExceptionalOption.getDex());
                            totMainPower = Integer.parseInt(itemTotalOption.getAttack_power()) + Integer.parseInt(itemExceptionalOption.getAttack_power());
                            addOptionMainPower = Integer.parseInt(itemAddOption.getAttack_power());
                            baseOptionMainPower = Integer.parseInt(itemBaseOption.getAttack_power());
                            etcPower = Integer.parseInt(itemEtcOption.getAttack_power());
                            starForcePower = Integer.parseInt(itemStarforceOption.getAttack_power());
                            break;
                        case "덱힘":
                            totMainStat = Integer.parseInt(itemTotalOption.getDex()) + Integer.parseInt(itemExceptionalOption.getDex());
                            totSubStat = Integer.parseInt(itemTotalOption.getStr()) + Integer.parseInt(itemExceptionalOption.getStr());
                            totMainPower = Integer.parseInt(itemTotalOption.getAttack_power()) + Integer.parseInt(itemExceptionalOption.getAttack_power());
                            addOptionMainPower = Integer.parseInt(itemAddOption.getAttack_power());
                            baseOptionMainPower = Integer.parseInt(itemBaseOption.getAttack_power());
                            etcPower = Integer.parseInt(itemEtcOption.getAttack_power());
                            starForcePower = Integer.parseInt(itemStarforceOption.getAttack_power());
                            break;
                        case "인럭":
                            totMainStat = Integer.parseInt(itemTotalOption.getInt()) + Integer.parseInt(itemExceptionalOption.getInt());
                            totSubStat = Integer.parseInt(itemTotalOption.getLuk()) + Integer.parseInt(itemExceptionalOption.getLuk());
                            totMainPower = Integer.parseInt(itemTotalOption.getMagic_power()) + Integer.parseInt(itemExceptionalOption.getMagic_power());
                            addOptionMainPower = Integer.parseInt(itemAddOption.getMagic_power());
                            baseOptionMainPower = Integer.parseInt(itemBaseOption.getMagic_power());
                            etcPower = Integer.parseInt(itemEtcOption.getMagic_power());
                            starForcePower = Integer.parseInt(itemStarforceOption.getMagic_power());
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

                    String potentialOption1 = itemEquipmentVo.getPotential_option_1();
                    String potentialOption2 = itemEquipmentVo.getPotential_option_2();
                    String potentialOption3 = itemEquipmentVo.getPotential_option_3();
                    String additionalPotentialOption1 = itemEquipmentVo.getAdditional_potential_option_1();
                    String additionalPotentialOption2 = itemEquipmentVo.getAdditional_potential_option_2();
                    String additionalPotentialOption3 = itemEquipmentVo.getAdditional_potential_option_3();
                    Object[] potentialOption = {potentialOption1, potentialOption2, potentialOption3};
                    Object[] additionalPotentialOption = {additionalPotentialOption1, additionalPotentialOption2, additionalPotentialOption3};

                    String totAllStat = itemTotalOption.getAll_stat();
                    System.out.println("itemExceptionalOption = " + itemExceptionalOption);

                    itemMainStat += totMainStat;
                    itemSubStat += totSubStat;
                    itemAllStat += Integer.parseInt(totAllStat);
                    itemMainPower += totMainPower;
                    itemDamage += Integer.parseInt(itemTotalOption.getDamage()) + Integer.parseInt(itemTotalOption.getBoss_damage());


                    for (Object option : potentialOption) {
                        percentMainStat += calItemPercentOption((String) option, mainStat);
                        percentSubStat += calItemPercentOption((String) option, subStat);
                        percentAllStat += calItemPercentOption((String) option, "올스탯");
                        percentMainPower += calItemPercentOption((String) option, mainPower);
                        itemOptionDamage += calItemPercentOption((String) option, "데미지");
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
                        itemOptionDamage += calItemPercentOption((String) additionalOption, "데미지");
                        itemCriDamage += calItemPercentOption((String) additionalOption, "크리티컬 데미지");


                        justOptionMainStat += calItemJustStatOption((String) additionalOption, mainStat, level);
                        justOptionSubStat += calItemJustStatOption((String) additionalOption, subStat, level);
                        justOptionAllStat += calItemJustStatOption((String) additionalOption, "올스탯", level);
                        justOptionMainPower += calItemJustStatOption((String) additionalOption, mainPower, level);
                    }


                }
            }
        }

        System.out.println("scrollUpgrade = " + scrollUpgrade);
        System.out.println("starForce = " + starForce);
        System.out.println("etcPower = " + etcPower);

        itemOptionDamage = itemOptionDamage - itemCriDamage;
        System.out.println("itemEquipment = " + itemEquipment);
        System.out.println("addOptionMainPower = " + addOptionMainPower);
        System.out.println("baseOptionMainPower = " + baseOptionMainPower);
        System.out.println("weaponPart = " + weaponPart);

        int etcConstantCorrection = calWeaponEtcConstantCorrection(scrollUpgrade, starForce, etcPower, weaponName);
        System.out.println("etcConstantCorrection = " + etcConstantCorrection);

        int correctionConstantBase = calWeaponBasicConstantCorrection(weaponName, baseOptionMainPower);
        System.out.println("correctionConstantBase = " + correctionConstantBase);

        int addGrade = calWeaponAddOptionGradeV2(weaponName, weaponPart, addOptionMainPower);
        System.out.println("addGrade = " + addGrade);
        int correctionConstantAdd = calWeaponAddConstantCorrection(weaponName, addGrade);
        int resultEtc = 0;
        if (starForcePower > 1 && !Objects.equals(weaponPart, "활")) {
            resultEtc = etcConstantCorrection - (starForcePower + baseOptionMainPower + etcPower);
        }
        System.out.println("resultEtc = " + resultEtc);
        int resultAdd = 0;
        if (!Objects.equals(weaponPart, "활")) {
            resultAdd = correctionConstantAdd - addOptionMainPower;
        }
        System.out.println("correctionConstantAdd = " + correctionConstantAdd);
        int coPower = etcConstantCorrection + correctionConstantAdd;
        int resultPower = 0;
        if (!Objects.equals(weaponPart, "활")
                && !Objects.equals(weaponPart, "듀얼 보우건")
                && !Objects.equals(weaponPart, "에인션트 보우")
                && !Objects.equals(weaponPart, "브레스 슈터")
                && !Objects.equals(weaponPart, "단검")
                && !Objects.equals(weaponPart, "체인")
                && !Objects.equals(weaponPart, "부채")
                && !Objects.equals(weaponPart, "차크람")) {
            resultPower = coPower - itemMainPower;
        }
        System.out.println("resultPower = " + resultPower);
        System.out.println("resultAdd = " + resultAdd);
        if (weaponName.contains("제네시스")) {
            weaponStatDto.setLiberation(1.1);
        } else {
            weaponStatDto.setLiberation(1.0);
        }

        weaponStatDto.setDamagePercent(itemOptionDamage);
        weaponStatDto.setPowerPercent(percentMainPower);
        weaponStatDto.setMainStatPercent(percentMainStat);
        weaponStatDto.setSubStatPercent(percentSubStat);
        weaponStatDto.setJustPowerStat(justOptionMainPower);

        weaponStatDto.setJustMainStat(justOptionMainStat);
        weaponStatDto.setJustSubStat(justOptionSubStat);
        weaponStatDto.setJustAllStat(justOptionAllStat);
        weaponStatDto.setAllStatPercent(percentAllStat);

        weaponStatDto.setTotalAllStat(itemAllStat);
        weaponStatDto.setTotalMainPower(itemMainPower);
        weaponStatDto.setTotalMainStat(itemMainStat);
        weaponStatDto.setTotalSubStat(itemSubStat);
        weaponStatDto.setTotalDamage(itemDamage);

        weaponStatDto.setConstantCorrection(resultPower);
        weaponStatDto.setBonusGrade(addGrade);

        return weaponStatDto;
    }

    private int calItemPercentOption(String option, String stat) {
        int statPercent = 0;
        if (option == null) {
            return statPercent;
        } else {
            if (option.contains("%") && option.contains(stat)) {
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

    private int calWeaponBasicConstantCorrection(String weaponName, int weaponPower) {
        int basicCorrection = 0;
        if (weaponName.contains("파프니르")|| weaponName.contains("7형")) {
            basicCorrection = 160 - weaponPower;
        } else if (weaponName.contains("앱솔랩스")|| weaponName.contains("8형")) {
            basicCorrection = 192 - weaponPower;
        } else if (weaponName.contains("아케인셰이드")|| weaponName.contains("9형")) {
            basicCorrection = 276 - weaponPower;
        }
        return basicCorrection;
    }

    private int calWeaponAddOptionGradeV2(String weaponName, String weaponPart, int weaponPower) {
        int grade = 0;

        Map<String, Map<Integer, Integer>> weaponData = new HashMap<>();
        if (weaponName.contains("파프니르")|| weaponName.contains("7형")) {
            weaponData.put("아대", Map.of(36, 1, 28, 2, 21, 3, 16, 4, 11, 5));
            weaponData.put("건", Map.of(52, 1, 40, 2, 31, 3, 22, 4, 15, 5));
            weaponData.put("폴암", Map.of(63, 1, 49, 2, 38, 3, 27, 4, 19, 5));

            weaponData.put("너클", Map.of(53, 1, 41, 2, 31, 3, 23, 4, 16, 5));
            weaponData.put("소울 슈터", Map.of(53, 1, 41, 2, 31, 3, 23, 4, 16, 5));
            weaponData.put("에너지 소드", Map.of(53, 1, 41, 2, 31, 3, 23, 4, 16, 5));
            weaponData.put("건틀렛 리볼버", Map.of(53, 1, 41, 2, 31, 3, 23, 4, 16, 5));

            weaponData.put("활", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));
            weaponData.put("차크람", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));
            weaponData.put("듀얼 보우건", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));
            weaponData.put("에인션트 보우", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));
            weaponData.put("체인", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));
            weaponData.put("단검", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));
            weaponData.put("부채", Map.of(66, 1, 52, 2, 39, 3, 29, 4, 20, 5));

            weaponData.put("태도", Map.of(64, 1, 47, 2, 32, 3, 20, 4, 9, 5));
            weaponData.put("대검", Map.of(64, 1, 47, 2, 32, 3, 20, 4, 9, 5));

            weaponData.put("한손검", Map.of(68, 1, 53, 2, 40, 3, 29, 4, 20, 5));
            weaponData.put("한손도끼", Map.of(68, 1, 53, 2, 40, 3, 29, 4, 20, 5));
            weaponData.put("한손둔기", Map.of(68, 1, 53, 2, 40, 3, 29, 4, 20, 5));
            weaponData.put("석궁", Map.of(68, 1, 53, 2, 40, 3, 29, 4, 20, 5));
            weaponData.put("케인", Map.of(68, 1, 53, 2, 40, 3, 29, 4, 20, 5));

            weaponData.put("두손검", Map.of(71, 1, 55, 2, 42, 3, 31, 4, 21, 5));
            weaponData.put("데스페라도", Map.of(71, 1, 55, 2, 42, 3, 31, 4, 21, 5));
            weaponData.put("튜너", Map.of(71, 1, 55, 2, 42, 3, 31, 4, 21, 5));
            weaponData.put("두손도끼", Map.of(71, 1, 55, 2, 42, 3, 31, 4, 21, 5));
            weaponData.put("두손둔기", Map.of(71, 1, 55, 2, 42, 3, 31, 4, 21, 5));
            weaponData.put("창", Map.of(71, 1, 55, 2, 42, 3, 31, 4, 21, 5));

            weaponData.put("핸드캐논", Map.of(72, 1, 56, 2, 43, 3, 31, 4, 21, 5));

            weaponData.put("완드", Map.of(83, 1, 65, 2, 49, 3, 36, 4, 25, 5));
            weaponData.put("샤이닝 로드", Map.of(83, 1, 65, 2, 49, 3, 36, 4, 25, 5));
            weaponData.put("ESP 리미터", Map.of(83, 1, 65, 2, 49, 3, 36, 4, 25, 5));
            weaponData.put("매직 건틀렛", Map.of(83, 1, 65, 2, 49, 3, 36, 4, 25, 5));

            weaponData.put("스태프", Map.of(84, 1, 66, 2, 50, 3, 36, 4, 25, 5));

        } else if (weaponName.contains("앱솔랩스")|| weaponName.contains("8형")) {
            weaponData.put("아대", Map.of(53, 1, 42, 2, 32, 3, 23, 4, 16, 5));
            weaponData.put("건", Map.of(77, 1, 60, 2, 46, 3, 33, 4, 23, 5));
            weaponData.put("폴암", Map.of(95, 1, 74, 2, 56, 3, 41, 4, 28, 5));

            weaponData.put("너클", Map.of(79, 1, 62, 2, 47, 3, 34, 4, 24, 5));
            weaponData.put("소울 슈터", Map.of(79, 1, 62, 2, 47, 3, 34, 4, 24, 5));
            weaponData.put("에너지 소드", Map.of(79, 1, 62, 2, 47, 3, 34, 4, 24, 5));
            weaponData.put("건틀렛 리볼버", Map.of(79, 1, 62, 2, 47, 3, 34, 4, 24, 5));

            weaponData.put("활", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));
            weaponData.put("차크람", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));
            weaponData.put("듀얼 보우건", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));
            weaponData.put("에인션트 보우", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));
            weaponData.put("체인", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));
            weaponData.put("단검", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));
            weaponData.put("부채", Map.of(99, 1, 77, 2, 59, 3, 43, 4, 29, 5));

            weaponData.put("태도", Map.of(76, 1, 56, 2, 38, 3, 23, 4, 11, 5));
            weaponData.put("대검", Map.of(76, 1, 56, 2, 38, 3, 23, 4, 11, 5));


            weaponData.put("한손검", Map.of(101, 1, 70, 2, 60, 3, 44, 4, 30, 5));
            weaponData.put("한손도끼", Map.of(101, 1, 70, 2, 60, 3, 44, 4, 30, 5));
            weaponData.put("한손둔기", Map.of(101, 1, 70, 2, 60, 3, 44, 4, 30, 5));
            weaponData.put("석궁", Map.of(101, 1, 70, 2, 60, 3, 44, 4, 30, 5));
            weaponData.put("케인", Map.of(101, 1, 70, 2, 60, 3, 44, 4, 30, 5));

            weaponData.put("두손검", Map.of(106, 1, 82, 2, 63, 3, 46, 4, 31, 5));
            weaponData.put("데스페라도", Map.of(106, 1, 82, 2, 63, 3, 46, 4, 31, 5));
            weaponData.put("튜너", Map.of(106, 1, 82, 2, 63, 3, 46, 4, 31, 5));
            weaponData.put("두손도끼", Map.of(106, 1, 82, 2, 63, 3, 46, 4, 31, 5));
            weaponData.put("두손둔기", Map.of(106, 1, 82, 2, 63, 3, 46, 4, 31, 5));
            weaponData.put("창", Map.of(106, 1, 82, 2, 63, 3, 46, 4, 31, 5));

            weaponData.put("핸드캐논", Map.of(108, 1, 84, 2, 64, 3, 47, 4, 32, 5));

            weaponData.put("완드", Map.of(124, 1, 97, 2, 73, 3, 54, 4, 37, 5));
            weaponData.put("샤이닝 로드", Map.of(124, 1, 97, 2, 73, 3, 54, 4, 37, 5));
            weaponData.put("ESP 리미터", Map.of(124, 1, 97, 2, 73, 3, 54, 4, 37, 5));
            weaponData.put("매직 건틀렛", Map.of(124, 1, 97, 2, 73, 3, 54, 4, 37, 5));

            weaponData.put("스태프", Map.of(126, 1, 98, 2, 75, 3, 54, 4, 37, 5));

        } else if (weaponName.contains("아케인셰이드")|| weaponName.contains("9형")) {
            weaponData.put("아대", Map.of(92, 1, 72, 2, 55, 3, 40, 4, 27, 5));
            weaponData.put("건", Map.of(133, 1, 104, 2, 79, 3, 58, 4, 39, 5));
            weaponData.put("폴암", Map.of(163, 1, 127, 2, 96, 3, 70, 4, 43, 5));

            weaponData.put("너클", Map.of(136, 1, 106, 2, 81, 3, 59, 4, 40, 5));
            weaponData.put("소울 슈터", Map.of(136, 1, 106, 2, 81, 3, 59, 4, 40, 5));
            weaponData.put("에너지 소드", Map.of(136, 1, 106, 2, 81, 3, 59, 4, 40, 5));
            weaponData.put("건틀렛 리볼버", Map.of(136, 1, 106, 2, 81, 3, 59, 4, 406, 5));

            weaponData.put("활", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));
            weaponData.put("차크람", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));
            weaponData.put("듀얼 보우건", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));
            weaponData.put("에인션트 보우", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));
            weaponData.put("체인", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));
            weaponData.put("단검", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));
            weaponData.put("부채", Map.of(179, 1, 133, 2, 101, 3, 73, 4, 50, 5));

            weaponData.put("태도", Map.of(131, 1, 95, 2, 65, 3, 40, 4, 18, 5));
            weaponData.put("대검", Map.of(131, 1, 95, 2, 65, 3, 40, 4, 18, 5));


            weaponData.put("한손검", Map.of(175, 1, 136, 2, 103, 3, 75, 4, 51, 5));
            weaponData.put("한손도끼", Map.of(175, 1, 136, 2, 103, 3, 75, 4, 51, 5));
            weaponData.put("한손둔기", Map.of(175, 1, 136, 2, 103, 3, 75, 4, 51, 5));
            weaponData.put("석궁", Map.of(175, 1, 136, 2, 103, 3, 75, 4, 51, 5));
            weaponData.put("케인", Map.of(175, 1, 136, 2, 103, 3, 75, 4, 51, 5));

            weaponData.put("두손검", Map.of(182, 1, 142, 2, 108, 3, 78, 4, 54, 5));
            weaponData.put("데스페라도", Map.of(182, 1, 142, 2, 108, 3, 78, 4, 54, 5));
            weaponData.put("튜너", Map.of(182, 1, 142, 2, 108, 3, 78, 4, 54, 5));
            weaponData.put("두손도끼", Map.of(182, 1, 142, 2, 108, 3, 78, 4, 54, 5));
            weaponData.put("두손둔기", Map.of(182, 1, 142, 2, 108, 3, 78, 4, 54, 5));
            weaponData.put("창", Map.of(182, 1, 142, 2, 108, 3, 78, 4, 541, 5));

            weaponData.put("핸드캐논", Map.of(186, 1, 145, 2, 110, 3, 80, 4, 55, 5));

            weaponData.put("완드", Map.of(214, 1, 167, 2, 126, 3, 92, 4, 63, 5));
            weaponData.put("샤이닝 로드", Map.of(214, 1, 167, 2, 126, 3, 92, 4, 63, 5));
            weaponData.put("ESP 리미터", Map.of(214, 1, 167, 2, 126, 3, 92, 4, 63, 5));
            weaponData.put("매직 건틀렛", Map.of(214, 1, 167, 2, 126, 3, 92, 4, 635, 5));

            weaponData.put("스태프", Map.of(218, 1, 170, 2, 129, 3, 94, 4, 64, 5));

        }
        if (weaponData.containsKey(weaponPart)) {
            grade = weaponData.get(weaponPart).getOrDefault(weaponPower, 0);
        }

        return grade;
    }

    private int calWeaponAddConstantCorrection(String weaponName, int grade) {
        int addCorrection = 0;
        if (weaponName.contains("파프니르") || weaponName.contains("7형")) {
            switch (grade) {
                case 1:
                    addCorrection = 66;
                    break;
                case 2:
                    addCorrection = 52;
                    break;
                case 3:
                    addCorrection = 39;
                    break;
                case 4:
                    addCorrection = 29;
                    break;
                case 5:
                    addCorrection = 20;
                    break;
                default:
                    addCorrection = 0;
                    break;
            }
        } else if (weaponName.contains("앱솔랩스") || weaponName.contains("8형")) {
            switch (grade) {
                case 1:
                    addCorrection = 99;
                    break;
                case 2:
                    addCorrection = 77;
                    break;
                case 3:
                    addCorrection = 59;
                    break;
                case 4:
                    addCorrection = 43;
                    break;
                case 5:
                    addCorrection = 29;
                    break;
                default:
                    addCorrection = 0;
                    break;
            }
        } else if (weaponName.contains("아케인셰이드") || weaponName.contains("9형")) {
            switch (grade) {
                case 1:
                    addCorrection = 170;
                    break;
                case 2:
                    addCorrection = 133;
                    break;
                case 3:
                    addCorrection = 101;
                    break;
                case 4:
                    addCorrection = 73;
                    break;
                case 5:
                    addCorrection = 50;
                    break;
                default:
                    addCorrection = 0;
                    break;
            }
        }
        return addCorrection;
    }

    private int calWeaponEtcConstantCorrection(int scrollUpgrade, int starForce, int etcPower, String weaponName) {
        int starForceChangeValue = 0;
        int papWeaponPower = 160 + etcPower;
        int appWeaponPower = 190 + etcPower;
        int akeWeaponPower = 276 + etcPower;
        int result = 0;
        if (starForce == 0) {
            if (weaponName.contains("파프니르") || weaponName.contains("7형")) {
                result = papWeaponPower;
            } else if (weaponName.contains("앱솔랩스") || weaponName.contains("8형")) {
                result = appWeaponPower;
            } else if (weaponName.contains("아케인") || weaponName.contains("9형")) {
                result = akeWeaponPower;
            }
        } else {
            if ((weaponName.contains("파프니르") || weaponName.contains("7형")) && starForce > 0) {
                int starPower = 10;
                for (int i = 1; i <= starForce; i++) {
                    if (i < 16) {
                        starForceChangeValue = (papWeaponPower / 50) + 1;
                        papWeaponPower += starForceChangeValue;
                    } else if (i == 16) {
                        papWeaponPower += 8;
                    } else if (i < 19) {
                        papWeaponPower += 9;
                    } else if (i < 23) {
                        papWeaponPower += starPower;
                        starPower++;
                    }
                }
                result = papWeaponPower;
            } else if ((weaponName.contains("앱솔랩스") || weaponName.contains("8형")) && starForce > 0) {
                int starPower = 10;
                for (int i = 1; i <= starForce; i++) {
                    System.out.println("i = " + i);
                    if (i < 16) {
                        starForceChangeValue = (appWeaponPower / 50) + 1;
                        appWeaponPower += starForceChangeValue;
                        System.out.println("starForceChangeValue = " + starForceChangeValue);
                        System.out.println("appWeaponPower = " + appWeaponPower);
                    } else if (i < 18) {
                        appWeaponPower += 9;
                        System.out.println("appWeaponPower = " + appWeaponPower);
                    } else if (i < 23) {
                        appWeaponPower += starPower;
                        starPower++;
                    }
                }
                result = appWeaponPower;
            } else if ((weaponName.contains("아케인셰이드")|| weaponName.contains("9형")) && starForce > 0) {
                int starPower = 15;
                for (int i = 1; i <= starForce; i++) {
                    if (i < 16) {
                        starForceChangeValue = (akeWeaponPower / 50) + 1;
                        akeWeaponPower += starForceChangeValue;
                    } else if (i < 18) {
                        akeWeaponPower += 13;
                    } else if (i < 20) {
                        akeWeaponPower += 14;
                    } else if (i < 23) {
                        akeWeaponPower += starPower;
                        starPower++;
                    }
                }
                result = akeWeaponPower;
            }
        }
        return result;
    }


}
