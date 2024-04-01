package com.openapi.maplestory.liberation.domain.dto.innerdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UnAppliedDto {
    @Id @GeneratedValue
    private int id;
    private int symbol;
    private int mainStat;
    private int subStat;

    private double power;
    private double criDamage;
    private double damage;

}
