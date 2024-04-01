package com.openapi.maplestory.liberation.domain.dto.innerdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeaponDto {
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

}
