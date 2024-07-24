package com.openapi.maplestory.liberation.repository.dto.innerdto;

import com.openapi.maplestory.liberation.domain.entity.AppliedStat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppliedStatDto {
    private int id;
    private int mainStat;
    private int mainStatPercent;

    private int subStat;
    private int subStatPercent;

    private int power;
    private int powerPercent;

    private double damage;
    private double criDamage;

    private int subStrStat;
    private int subStrStatPercent;

    private double rebootLastDamage;

    private int simpleLiberate;

    public AppliedStat toEntity(){
        return AppliedStat.builder()
                .mainStat(mainStat)
                .mainStatPercent(mainStatPercent)
                .subStat(subStat)
                .subStatPercent(subStatPercent)
                .subStrStat(subStrStat)
                .subStrStatPercent(subStrStatPercent)
                .power(power)
                .powerPercent(powerPercent)
                .damage(damage)
                .criDamage(criDamage)
                .simpleLiberate(simpleLiberate)
                .rebootLastDamage(rebootLastDamage)
                .build();
    }

}
