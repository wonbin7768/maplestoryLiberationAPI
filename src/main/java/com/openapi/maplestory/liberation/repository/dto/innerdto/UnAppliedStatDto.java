package com.openapi.maplestory.liberation.repository.dto.innerdto;

import com.openapi.maplestory.liberation.domain.entity.UnAppliedStat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnAppliedStatDto {
    private int id;
    private int symbol;
    private int mainStat;
    private int subStat;
    private int strSubStat;

    private double power;
    private double criDamage;
    private double damage;

    public UnAppliedStat toEntity(){
        return UnAppliedStat.builder()
                .symbol(symbol)
                .mainStat(mainStat)
                .subStat(subStat)
                .strSubStat(strSubStat)
                .power(power)
                .criDamage(criDamage)
                .damage(damage)
                .build();
    }

}
