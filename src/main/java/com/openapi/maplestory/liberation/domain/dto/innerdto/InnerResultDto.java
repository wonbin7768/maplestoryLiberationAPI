package com.openapi.maplestory.liberation.domain.dto.innerdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InnerResultDto {

    private int unAppliedMainStat;
    private int unAppliedSubStat;

    private int AppliedMainStat;
    private int AppliedSubStat;

    private int MainPower;
    private int criDamage;
    private int Damage;

}
