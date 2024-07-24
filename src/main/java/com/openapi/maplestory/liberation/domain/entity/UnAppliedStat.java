package com.openapi.maplestory.liberation.domain.entity;

import com.openapi.maplestory.liberation.repository.dto.innerdto.UnAppliedStatDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnAppliedStat {
    @Id @GeneratedValue
    private int id;
    private int symbol;
    private int mainStat;
    private int subStat;
    private int strSubStat;

    private double power;
    private double criDamage;
    private double damage;

    @Builder
    private UnAppliedStat(int symbol, int mainStat, int subStat,int strSubStat, double power, double criDamage, double damage){
        this.symbol = symbol;
        this.mainStat = mainStat;
        this.subStat = subStat;
        this.strSubStat = strSubStat;
        this.power = power;
        this.criDamage = criDamage;
        this.damage = damage;
    }
    public UnAppliedStatDto toResponseDto(){
        return UnAppliedStatDto.builder()
                .id(id)
                .symbol(symbol)
                .mainStat(mainStat)
                .subStat(subStat)
                .strSubStat(strSubStat)
                .power(power)
                .criDamage(criDamage)
                .damage(damage)
                .build();
    }

    @Override
    public String toString() {
        return "UnAppliedStat{" +
                "id=" + id +
                ", symbol=" + symbol +
                ", mainStat=" + mainStat +
                ", subStat=" + subStat +
                ", power=" + power +
                ", criDamage=" + criDamage +
                ", damage=" + damage +
                '}';
    }
}
