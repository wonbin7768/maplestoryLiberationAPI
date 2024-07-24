package com.openapi.maplestory.liberation.repository.dto.innerdto;

import com.openapi.maplestory.liberation.domain.entity.WeaponStat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeaponStatDto {
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

    public WeaponStat toEntity(){
        return WeaponStat.builder()
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

}
