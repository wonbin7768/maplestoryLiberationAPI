package com.openapi.maplestory.liberation.repository.dto.innerdto;

import com.openapi.maplestory.liberation.domain.entity.ResultInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultInfoDto {
    private Long id;
    private BasicInfoDto basicInfoDto;
    private UnAppliedStatDto unAppliedStatDto;
    private AppliedStatDto appliedStatDto;
    private WeaponStatDto weaponStatDto;
    private ChangeSetDto changeSetDto;
    private long combatPower;
    private long simpleCombatPower;
    private long detailCombatPower;

    public static ResultInfoDto createResult(BasicInfoDto basicInfoDto, UnAppliedStatDto unAppliedStatDto, AppliedStatDto appliedStatDto, WeaponStatDto weaponStatDto,ChangeSetDto changeSetDto,long combatPower , long simpleCombatPower , long detailCombatPower){
        ResultInfoDto resultInfoDto = new ResultInfoDto();
        resultInfoDto.setBasicInfoDto(basicInfoDto);
        resultInfoDto.setUnAppliedStatDto(unAppliedStatDto);
        resultInfoDto.setAppliedStatDto(appliedStatDto);
        resultInfoDto.setWeaponStatDto(weaponStatDto);
        resultInfoDto.setChangeSetDto(changeSetDto);
        resultInfoDto.setCombatPower(combatPower);
        resultInfoDto.setSimpleCombatPower(simpleCombatPower);
        resultInfoDto.setDetailCombatPower(detailCombatPower);
        return resultInfoDto;
    }

    public ResultInfoDto(ResultInfo resultInfo){
        id = resultInfo.getId();
        basicInfoDto = resultInfo.getBasicInfo().toResponseDto();
        unAppliedStatDto = resultInfo.getUnAppliedStat().toResponseDto();
        appliedStatDto = resultInfo.getAppliedStat().toResponseDto();
        weaponStatDto = resultInfo.getWeaponStat().toResponseDto();
        changeSetDto = resultInfo.getChangeSet().toResponseDto();
        combatPower = resultInfo.getCombatPower();
        simpleCombatPower = resultInfo.getSimpleCombatPower();
        detailCombatPower = resultInfo.getDetailCombatPower();

    }
    public ResultInfo toEntity(){
        ResultInfo resultInfo = ResultInfo.builder()
                .basicInfo(basicInfoDto.toEntity())
                .unAppliedStat(unAppliedStatDto.toEntity())
                .appliedStat(appliedStatDto.toEntity())
                .weaponStat(weaponStatDto.toEntity())
                .changeSet(changeSetDto.toEntity())
                .combatPower(combatPower)
                .simpleCombatPower(simpleCombatPower)
                .detailCombatPower(detailCombatPower)
                .build();
        return resultInfo;
    }

    public void updateSimpleLiberate(ResultInfo innerResult , long simpleCombatPower) {
        innerResult.updateSimpleCombatPower(simpleCombatPower);
    }
    public void updateDetailLiberate(ResultInfo innerResult , long detailCombatPower) {
        innerResult.updateDetailCombatPower(detailCombatPower);
    }
}
