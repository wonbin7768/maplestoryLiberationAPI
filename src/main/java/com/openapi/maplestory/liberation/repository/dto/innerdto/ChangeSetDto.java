package com.openapi.maplestory.liberation.repository.dto.innerdto;

import com.openapi.maplestory.liberation.domain.entity.ChangeSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeSetDto {
    private int id;
    private int mainStat;
    private int subStat;
    private int power;
    private double damage;
    private double criDamage;

    public ChangeSet toEntity(){
        return ChangeSet.builder()
                .mainStat(mainStat)
                .subStat(subStat)
                .power(power)
                .damage(damage)
                .criDamage(criDamage)
                .build();
    }

}
