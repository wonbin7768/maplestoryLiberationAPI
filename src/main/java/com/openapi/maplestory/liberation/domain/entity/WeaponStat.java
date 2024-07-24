package com.openapi.maplestory.liberation.domain.entity;

import com.openapi.maplestory.liberation.repository.dto.innerdto.WeaponStatDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Getter
@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeaponStat {
    @Id @GeneratedValue
    private int id;

    private String weaponName;
    private String soulOption;

    private int totalMainStat;
    private int totalSubStat;
    private int totalAllStat;
    private int totalDamage;
    private int totalMainPower;

    private int damagePercent;
    private int powerPercent;
    private int justPowerStat;
    private int mainStatPercent;
    private int subStatPercent;
    private int justMainStat;
    private int justSubStat;
    private int allStatPercent;
    private int justAllStat;


    private int bonusMainStat;
    private int bonusSubStat;
    private int bonusAllStat;
    private int bonusBossDamage;
    private int bonusMainPower;
    private int bonusGrade;

    private int constantCorrection;

    private double liberation;

    @Builder(toBuilder = true)
    private WeaponStat(
            String weaponName, String soulOption, int totalMainStat, int totalSubStat, int totalAllStat, int totalDamage, int totalMainPower,
            int damagePercent, int powerPercent, int justPowerStat, int mainStatPercent, int subStatPercent, int justMainStat, int justSubStat,
            int allStatPercent, int justAllStat, int bonusMainStat, int bonusSubStat, int bonusAllStat, int bonusBossDamage, int bonusMainPower,
            int bonusGrade, int constantCorrection, double liberation
    ){
        this.weaponName = weaponName;
        this.soulOption = soulOption;
        this.totalMainStat = totalMainStat;
        this.totalSubStat = totalSubStat;
        this.totalAllStat = totalAllStat;
        this.totalDamage = totalDamage;
        this.totalMainPower = totalMainPower;
        this.damagePercent = damagePercent;
        this.powerPercent = powerPercent;
        this.justPowerStat = justPowerStat;
        this.mainStatPercent = mainStatPercent;
        this.subStatPercent = subStatPercent;
        this.justMainStat = justMainStat;
        this.justSubStat = justSubStat;
        this.allStatPercent = allStatPercent;
        this.justAllStat = justAllStat;
        this.bonusMainStat = bonusMainStat;
        this.bonusSubStat = bonusSubStat;
        this.bonusAllStat = bonusAllStat;
        this.bonusBossDamage = bonusBossDamage;
        this.bonusMainPower = bonusMainPower;
        this.bonusGrade = bonusGrade;
        this.constantCorrection = constantCorrection;
        this.liberation = liberation;
    }
    public void updateWeaponStat(
            WeaponStat weaponStat
            ){
        this.weaponName = weaponStat.weaponName;
        this.soulOption = weaponStat.soulOption;
        this.totalMainStat = weaponStat.totalMainStat;
        this.totalSubStat = weaponStat.totalSubStat;
        this.totalAllStat = weaponStat.totalAllStat;
        this.totalDamage = weaponStat.totalDamage;
        this.totalMainPower = weaponStat.totalMainPower;
        this.damagePercent = weaponStat.damagePercent;
        this.powerPercent = weaponStat.powerPercent;
        this.justPowerStat = weaponStat.justPowerStat;
        this.mainStatPercent = weaponStat.mainStatPercent;
        this.subStatPercent = weaponStat.subStatPercent;
        this.justMainStat = weaponStat.justMainStat;
        this.justSubStat = weaponStat.justSubStat;
        this.allStatPercent = weaponStat.allStatPercent;
        this.justAllStat = weaponStat.justAllStat;
        this.bonusMainStat = weaponStat.bonusMainStat;
        this.bonusSubStat = weaponStat.bonusSubStat;
        this.bonusAllStat = weaponStat.bonusAllStat;
        this.bonusBossDamage = weaponStat.bonusBossDamage;
        this.bonusMainPower = weaponStat.bonusMainPower;
        this.bonusGrade = weaponStat.bonusGrade;
        this.constantCorrection = weaponStat.constantCorrection;
        this.liberation = weaponStat.liberation;
    }
    public WeaponStatDto toResponseDto(){
        return WeaponStatDto.builder()
                .id(id)
                .allStatPercent(allStatPercent)
                .bonusAllStat(bonusAllStat)
                .bonusBossDamage(bonusBossDamage)
                .bonusGrade(bonusGrade)
                .bonusMainPower(bonusMainPower)
                .bonusMainStat(bonusMainStat)
                .bonusSubStat(bonusSubStat)
                .constantCorrection(constantCorrection)
                .damagePercent(damagePercent)
                .justAllStat(justAllStat)
                .justMainStat(justMainStat)
                .justPowerStat(justPowerStat)
                .justSubStat(justSubStat)
                .powerPercent(powerPercent)
                .liberation(liberation)
                .weaponName(weaponName)
                .mainStatPercent(mainStatPercent)
                .soulOption(soulOption)
                .subStatPercent(subStatPercent)
                .totalAllStat(totalAllStat)
                .totalDamage(totalDamage)
                .totalMainPower(totalMainPower)
                .totalMainStat(totalMainStat)
                .totalSubStat(totalSubStat)
                .build();
    }

    @Override
    public String toString() {
        return "WeaponStat{" +
                "id=" + id +
                ", weaponName='" + weaponName + '\'' +
                ", soulOption='" + soulOption + '\'' +
                ", totalMainStat=" + totalMainStat +
                ", totalSubStat=" + totalSubStat +
                ", totalAllStat=" + totalAllStat +
                ", totalDamage=" + totalDamage +
                ", totalMainPower=" + totalMainPower +
                ", damagePercent=" + damagePercent +
                ", powerPercent=" + powerPercent +
                ", justPowerStat=" + justPowerStat +
                ", mainStatPercent=" + mainStatPercent +
                ", subStatPercent=" + subStatPercent +
                ", justMainStat=" + justMainStat +
                ", justSubStat=" + justSubStat +
                ", allStatPercent=" + allStatPercent +
                ", justAllStat=" + justAllStat +
                ", bonusMainStat=" + bonusMainStat +
                ", bonusSubStat=" + bonusSubStat +
                ", bonusAllStat=" + bonusAllStat +
                ", bonusBossDamage=" + bonusBossDamage +
                ", bonusMainPower=" + bonusMainPower +
                ", bonusGrade=" + bonusGrade +
                ", constantCorrection=" + constantCorrection +
                ", liberation=" + liberation +
                '}';
    }
}
