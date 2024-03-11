package com.openapi.maplestory.liberation.domain.dto.innerdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnAppliedDto {
    private int symbol;
    private int mainStat;
    private int subStat;

    private double power;
    private double criDamage;
    private double damage;

}
