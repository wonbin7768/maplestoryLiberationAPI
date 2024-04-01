package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.dto.innerdto.*;
import com.openapi.maplestory.liberation.repository.InnerResultDtoRepository;
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
    public InnerResultDto calCombatPower(Long id) {

        InnerResultDto innerResultDto = innerResultDtoRepository.findInnerResultDto(id);
        System.out.println("innerResultDto = " + innerResultDto);
        UnAppliedDto unAppliedDto = innerResultDto.getUnAppliedDto();
        AppliedDto appliedDto = innerResultDto.getAppliedDto();
        InnerDto innerDto = innerResultDto.getInnerDto();
        WeaponDto weaponDto = innerResultDto.getWeaponDto();
        double unAppliedDtoPower = unAppliedDto.getPower();
        double unAppliedDtoSymbol = unAppliedDto.getSymbol();
        double unAppliedDtoMainStat = unAppliedDto.getMainStat();
        double unAppliedDtoSubStat = unAppliedDto.getSubStat();
        double unAppliedDtoDamage = unAppliedDto.getDamage();
        double unAppliedDtoCriDamage = unAppliedDto.getCriDamage();

        double appliedDtoMainStat = appliedDto.getMainStat();
        double appliedDtoSubStat = appliedDto.getSubStat();
        double appliedDtoPower = appliedDto.getPower();
        double appliedDtoMainStatPercent = appliedDto.getMainStatPercent();
        double appliedDtoSubStatPercent = appliedDto.getSubStatPercent();
        double appliedDtoPowerPercent = appliedDto.getPowerPercent();
        double appliedDtoDamage = appliedDto.getDamage();
        double appliedDtoCriDamage = appliedDto.getCriDamage();

        int simpleLiberate = appliedDto.getSimpleLiberate();
        double constantCorrection = 0.0;
        if (simpleLiberate!=1){
            constantCorrection = weaponDto.getConstantCorrection();
        }
        double liberation = weaponDto.getLiberation();


        double buffStat = 0;
        double buffPower = 0;
        double buffDamage = 0;

        double etcPower = innerDto.getEtcPower();
        System.out.println("etcPower = " + etcPower);

        System.out.println("appliedDtoMainStatPercent = " + ((100 + appliedDtoMainStatPercent) / 100));
        double mainStat1 = (buffStat + appliedDtoMainStat) * ((100 + appliedDtoMainStatPercent) / 100);
        double mainStat2 = Math.floor(mainStat1 + unAppliedDtoMainStat + unAppliedDtoSymbol);
        double subStat1 = (buffStat + appliedDtoSubStat) * ((100 + appliedDtoSubStatPercent) / 100);
        double subStat2 = Math.floor(subStat1 + unAppliedDtoSubStat);
        double lastStat = (mainStat2 * 4 + subStat2) / 100;


        /**
         * 윈브
         double power = buffPower + unAppliedDtoPower + appliedDtoPower + constantCorrection + 9;
         */

        double power = buffPower + unAppliedDtoPower + appliedDtoPower + constantCorrection + etcPower;
        System.out.println("unAppliedDtoPower = " + unAppliedDtoPower);
        System.out.println("appliedDtoPower = " + appliedDtoPower);

        double lastPower = Math.floor(power * ((100 + appliedDtoPowerPercent) / 100));
        double lastDamage = (buffDamage + 100 + unAppliedDtoDamage + appliedDtoDamage) / 100;
        double lastCriDamage = (135 + unAppliedDtoCriDamage + appliedDtoCriDamage) / 100;

        long combatPower = (int) Math.floor(lastStat * lastPower * lastDamage * lastCriDamage * liberation);

        innerResultDto.setCombatPower(combatPower);
        innerResultDtoRepository.combatPowerUpdate(id, innerResultDto);

        System.out.println("mainStat1 = " + mainStat1);
        System.out.println("mainStat2 = " + mainStat2);
        System.out.println("subStat1 = " + subStat1);
        System.out.println("subStat2 = " + subStat2);
        System.out.println("power = " + power);

        System.out.println("lastStat = " + lastStat);
        System.out.println("lastPower = " + lastPower);
        System.out.println("lastDamage = " + lastDamage);
        System.out.println("lastCriDamage = " + lastCriDamage);

        System.out.println("combatPower = " + combatPower);

        return innerResultDto;
    }
}
