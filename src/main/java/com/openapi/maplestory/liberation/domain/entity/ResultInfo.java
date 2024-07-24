package com.openapi.maplestory.liberation.domain.entity;

import com.openapi.maplestory.liberation.repository.dto.innerdto.ResultInfoDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Getter
@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultInfo {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BasicInfo basicInfo;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UnAppliedStat unAppliedStat;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AppliedStat appliedStat;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WeaponStat weaponStat;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ChangeSet changeSet;
    private long combatPower;
    private long simpleCombatPower;
    private long detailCombatPower;


    @Builder(toBuilder = true)
    private ResultInfo(BasicInfo basicInfo, UnAppliedStat unAppliedStat, AppliedStat appliedStat, WeaponStat weaponStat,ChangeSet changeSet, long combatPower,long simpleCombatPower,long detailCombatPower) {
        this.basicInfo = basicInfo;
        this.unAppliedStat = unAppliedStat;
        this.appliedStat = appliedStat;
        this.weaponStat = weaponStat;
        this.combatPower = combatPower;
        this.changeSet = changeSet;
        this.simpleCombatPower = simpleCombatPower;
        this.detailCombatPower = detailCombatPower;
    }

    public void updateSimpleCombatPower(Long simpleCombatPower) {
        this.simpleCombatPower = simpleCombatPower;
    }
    public void updateDetailCombatPower(Long detailCombatPower) {
        this.detailCombatPower = detailCombatPower;
    }
    public ResultInfoDto toResponseDto() {
        return ResultInfoDto.builder()
                .id(id)
                .basicInfoDto(basicInfo.toResponseDto())
                .unAppliedStatDto(unAppliedStat.toResponseDto())
                .appliedStatDto(appliedStat.toResponseDto())
                .weaponStatDto(weaponStat.toResponseDto())
                .changeSetDto(changeSet.toResponseDto())
                .combatPower(combatPower)
                .simpleCombatPower(simpleCombatPower)
                .detailCombatPower(detailCombatPower)
                .build();
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "id=" + id +
                ", basicInfo=" + basicInfo.toString() +
                ", unAppliedStat=" + unAppliedStat.toString() +
                ", appliedStat=" + appliedStat.toString() +
                ", weaponStat=" + weaponStat.toString() +
                ", combatPower=" + combatPower +
                '}';
    }
}
