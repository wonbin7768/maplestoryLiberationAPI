package com.openapi.maplestory.liberation.domain.dto.innerdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppliedDto {
    @Id @GeneratedValue
    private int id;
    private int mainStat;
    private int mainStatPercent;

    private int subStat;
    private int subStatPercent;

    private int power;
    private int powerPercent;

    private double damage;
    private double criDamage;

    private int simpleLiberate;
}
