package com.openapi.maplestory.liberation.domain.service;

import com.openapi.maplestory.liberation.domain.dto.innerdto.AppliedDto;
import com.openapi.maplestory.liberation.domain.dto.innerdto.UnAppliedDto;
import com.openapi.maplestory.liberation.domain.dto.innerdto.WeaponDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalCombatPowerService {
    public int calCombatPower(UnAppliedDto unAppliedDto, AppliedDto appliedDto , WeaponDto weaponDto) {
        double mainStatPercent = (100 + (double) appliedDto.getMainStatPercent()) / 100;
        System.out.println("appliedDto.getMainStatPercent() = " + appliedDto.getMainStatPercent());
        System.out.println("mainStatPercent = " + mainStatPercent);
        double mainStat = Math.floor((double) appliedDto.getMainStat() * mainStatPercent) + unAppliedDto.getSymbol() + unAppliedDto.getMainStat();

        double subStatPercent = (100 + (double)appliedDto.getSubStatPercent()) / 100;
        System.out.println("appliedDto.getSubStatPercent() = " + appliedDto.getSubStatPercent());
        System.out.println("subStatPercent = " + subStatPercent);

        double subStat = Math.floor((double)appliedDto.getSubStat() * subStatPercent) + unAppliedDto.getSubStat();
        double stat = (mainStat * 4 + subStat) / 100;

        double powerPercent = Math.floor(100 + (double)appliedDto.getPowerPercent())/100;
        System.out.println("powerPercent = " + powerPercent);
        double power = (appliedDto.getPower() + weaponDto.getConstantCorrection() + 27) * powerPercent;
        double damage = (100 + (unAppliedDto.getDamage() + appliedDto.getDamage()))/100;
        double criDamage = (135 + ((unAppliedDto.getCriDamage() + appliedDto.getCriDamage())))/100;
        int combatPower = (int) Math.floor(stat * power * damage * criDamage);

        System.out.println("mainStat = " + mainStat);
        System.out.println("subStat = " + subStat);
        System.out.println("stat = " + stat);
        System.out.println("power = " + power);
        System.out.println("damage = " + damage);
        System.out.println("criDamage = " + criDamage);
        System.out.println("combatPower = " + combatPower);

        return 1;
    }
}
