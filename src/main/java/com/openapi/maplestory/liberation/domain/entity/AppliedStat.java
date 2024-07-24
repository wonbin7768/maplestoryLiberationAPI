package com.openapi.maplestory.liberation.domain.entity;

import com.openapi.maplestory.liberation.repository.dto.innerdto.AppliedStatDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppliedStat {
    @Id
    @GeneratedValue
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

    @Builder(toBuilder = true)
    private AppliedStat(int mainStat, int mainStatPercent, int subStat, int subStatPercent,int subStrStat ,int subStrStatPercent ,int power, int powerPercent, double damage, double criDamage, double rebootLastDamage, int simpleLiberate) {
        this.mainStat = mainStat;
        this.mainStatPercent = mainStatPercent;
        this.subStat = subStat;
        this.subStatPercent = subStatPercent;
        this.subStrStat = subStrStat;
        this.subStrStatPercent = subStrStatPercent;
        this.power = power;
        this.powerPercent = powerPercent;
        this.damage = damage;
        this.criDamage = criDamage;
        this.rebootLastDamage = rebootLastDamage;
        this.simpleLiberate = simpleLiberate;

    }
    public void updateAppliedStat(AppliedStat appliedStat){
        this.mainStat = appliedStat.mainStat;
        this.mainStatPercent = appliedStat.mainStatPercent;
        this.subStat = appliedStat.subStat;
        this.subStatPercent = appliedStat.subStatPercent;
        this.subStrStat = appliedStat.subStrStat;
        this.subStrStatPercent = appliedStat.subStrStatPercent;
        this.power = appliedStat.power;
        this.powerPercent = appliedStat.powerPercent;
        this.damage = appliedStat.damage;
        this.criDamage = appliedStat.criDamage;
        this.rebootLastDamage = appliedStat.rebootLastDamage;;
        this.simpleLiberate = appliedStat.simpleLiberate;
    }
    public AppliedStatDto toResponseDto(){
        return AppliedStatDto.builder()
                .id(id)
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

    @Override
    public String toString() {
        return "AppliedStat{" +
                "id=" + id +
                ", mainStat=" + mainStat +
                ", mainStatPercent=" + mainStatPercent +
                ", subStat=" + subStat +
                ", subStatPercent=" + subStatPercent +
                ", power=" + power +
                ", powerPercent=" + powerPercent +
                ", damage=" + damage +
                ", criDamage=" + criDamage +
                ", simpleLiberate=" + simpleLiberate +
                '}';
    }
}
