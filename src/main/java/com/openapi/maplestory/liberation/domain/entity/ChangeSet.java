package com.openapi.maplestory.liberation.domain.entity;

import com.openapi.maplestory.liberation.repository.dto.innerdto.ChangeSetDto;
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
public class ChangeSet {
    @Id
    @GeneratedValue
    private int id;

    private int mainStat;
    private int subStat;
    private int power;
    private double damage;
    private double criDamage;

    @Builder(toBuilder = true)
    private ChangeSet(int mainStat,  int subStat,  int power, double damage, double criDamage) {
        this.mainStat = mainStat;
        this.subStat = subStat;
        this.power = power;
        this.damage = damage;
        this.criDamage = criDamage;
    }
    public ChangeSetDto toResponseDto(){
        return ChangeSetDto.builder()
                .id(id)
                .mainStat(mainStat)
                .subStat(subStat)
                .power(power)
                .damage(damage)
                .criDamage(criDamage)
                .build();
    }

    @Override
    public String toString() {
        return "ChangeSet{" +
                "id=" + id +
                ", mainStat=" + mainStat +
                ", subStat=" + subStat +
                ", power=" + power +
                ", damage=" + damage +
                ", criDamage=" + criDamage +
                '}';
    }
}
