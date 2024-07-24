package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.repository.InnerResultDtoRepository;
import com.openapi.maplestory.liberation.repository.dto.innerdto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalCombatPowerService {
    private final InnerResultDtoRepository innerResultDtoRepository;

    @Transactional
    public ResultInfoDto calCombatPower(BasicInfoDto basicInfoDto, UnAppliedStatDto unAppliedStatDto, AppliedStatDto appliedStatDto, WeaponStatDto weaponStatDto, ChangeSetDto changeSetDto) {

        double unAppliedDtoPower = unAppliedStatDto.getPower();
        double unAppliedDtoSymbol = unAppliedStatDto.getSymbol();
        double unAppliedDtoMainStat = unAppliedStatDto.getMainStat();
        double unAppliedDtoSubStat = unAppliedStatDto.getSubStat();
        double unAppliedDtoDamage = unAppliedStatDto.getDamage();
        double unAppliedDtoCriDamage = unAppliedStatDto.getCriDamage();
        double unAppliedStatDtoStrSubStat = unAppliedStatDto.getStrSubStat();


        double appliedDtoMainStat = appliedStatDto.getMainStat();
        double appliedDtoSubStat = appliedStatDto.getSubStat();
        double appliedDtoPower = appliedStatDto.getPower();
        double appliedDtoMainStatPercent = appliedStatDto.getMainStatPercent();
        double appliedDtoSubStatPercent = appliedStatDto.getSubStatPercent();
        double appliedDtoPowerPercent = appliedStatDto.getPowerPercent();
        double appliedDtoDamage = appliedStatDto.getDamage();
        double appliedDtoCriDamage = appliedStatDto.getCriDamage();
        double appliedStatDtoSubStrStat = appliedStatDto.getSubStrStat();
        double appliedStatDtoSubStrStatPercent = appliedStatDto.getSubStrStatPercent();

        double rebootLastDamage = appliedStatDto.getRebootLastDamage();

        int simpleLiberate = appliedStatDto.getSimpleLiberate();
        int changeSetMainStat = 0;
        int changeSetSubStat = 0;
        int changeSetPower = 0;
        double changeSetDamage = 0.0;
        double changeCriDamage = 0.0;

        double constantCorrection = 0.0;
        if (simpleLiberate!=1){
            constantCorrection = weaponStatDto.getConstantCorrection();
        }
        if (simpleLiberate == 1 ){
            changeSetMainStat = changeSetDto.getMainStat();
            changeSetSubStat = changeSetDto.getSubStat();
            changeSetDamage = changeSetDto.getDamage();
            changeSetPower = changeSetDto.getPower();
            changeCriDamage = changeSetDto.getCriDamage();
            System.out.println("changeSetDto!! = " + changeSetDto);
        }
        double liberation = weaponStatDto.getLiberation();

        System.out.println("constantCorrection = " + constantCorrection);

        double etcPower = basicInfoDto.getEtcPower();
        System.out.println("etcPower = " + etcPower);

        System.out.println("appliedDtoMainStatPercent = " + ((100 + appliedDtoMainStatPercent) / 100));
        double mainStat1 = (  appliedDtoMainStat + changeSetMainStat) * ((100 + appliedDtoMainStatPercent) / 100);
        double mainStat2 = Math.floor(mainStat1 + unAppliedDtoMainStat + unAppliedDtoSymbol +27);
        double subStat1 = (appliedDtoSubStat + changeSetSubStat) * ((100 + appliedDtoSubStatPercent) / 100);
        double subStrStat = (appliedStatDtoSubStrStat) * ((100 + appliedStatDtoSubStrStatPercent)/100);
        double subStat2 = Math.floor(subStat1 + unAppliedDtoSubStat + unAppliedStatDtoStrSubStat + subStrStat);
        double lastStat = (mainStat2 * 4 + subStat2) / 100;

        /**
         * 윈브
         double power = buffPower + unAppliedDtoPower + appliedDtoPower + constantCorrection + 9;
         */

        double power =   unAppliedDtoPower + appliedDtoPower + constantCorrection + etcPower + changeSetPower ;
        System.out.println("unAppliedDtoPower = " + unAppliedDtoPower);
        System.out.println("appliedDtoPower = " + appliedDtoPower);

        double lastPower = Math.floor(power * ((100 + appliedDtoPowerPercent) / 100));
        double lastDamage = (  100 + unAppliedDtoDamage + appliedDtoDamage + changeSetDamage) / 100;
        double lastCriDamage = (135 + unAppliedDtoCriDamage + appliedDtoCriDamage + changeCriDamage) / 100;
        long combatPower = 0;
        long simpleCombatPower = 0;
        long detailCombatPower = 0;
        if (rebootLastDamage > 0) {
            if(simpleLiberate == 1){
                simpleCombatPower = (int) Math.floor(lastStat * lastPower * lastDamage * lastCriDamage * liberation * rebootLastDamage);
            }
            combatPower = (int) Math.floor(lastStat * lastPower * lastDamage * lastCriDamage * liberation * rebootLastDamage);
        } else {
            if (simpleLiberate == 1){
                simpleCombatPower = (int) Math.floor(lastStat * lastPower * lastDamage * lastCriDamage * liberation);
            }
            combatPower = (int) Math.floor(lastStat * lastPower * lastDamage * lastCriDamage * liberation);
        }
        ResultInfoDto resultInfoDto = ResultInfoDto.createResult(basicInfoDto, unAppliedStatDto, appliedStatDto, weaponStatDto, changeSetDto, combatPower, simpleCombatPower, detailCombatPower);
//        ResultInfoDto resultInfoDto = new ResultInfoDto();
//        ResultInfoDto build = resultInfoDto.builder()
//                .basicInfoDto(basicInfoDto)
//                .unAppliedStatDto(unAppliedStatDto)
//                .appliedStatDto(appliedStatDto)
//                .combatPower(combatPower)
//                .weaponStatDto(weaponStatDto)
//                .build();
//        System.out.println("build = " + build);
        System.out.println("mainStat1 = " + mainStat1);
        System.out.println("mainStat2 = " + mainStat2);
        System.out.println("subStat1 = " + subStat1);
        System.out.println("subStrStat = " + subStrStat);
        System.out.println("unAppliedStatDtoStrSubStat = " + unAppliedStatDtoStrSubStat);
        System.out.println("appliedStatDtoSubStrStatPercent = " + appliedStatDtoSubStrStatPercent);
        System.out.println("subStat2 = " + subStat2);
        System.out.println("power = " + power);

        System.out.println("lastStat = " + lastStat);
        System.out.println("lastPower = " + lastPower);
        System.out.println("lastDamage = " + lastDamage);
        System.out.println("lastCriDamage = " + lastCriDamage);

        System.out.println("combatPower = " + combatPower);

        return resultInfoDto;
    }

}
