package com.openapi.maplestory.liberation.domain.dto.innerdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InnerResultDto {
    @Id @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private InnerDto innerDto;
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private UnAppliedDto unAppliedDto;
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private AppliedDto appliedDto;
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private WeaponDto weaponDto;
    private long combatPower;

    public static InnerResultDto createResult(InnerDto innerDto, UnAppliedDto unAppliedDto ,AppliedDto appliedDto , WeaponDto weaponDto ){
        InnerResultDto innerResultDto = new InnerResultDto();
        innerResultDto.setInnerDto(innerDto);
        innerResultDto.setUnAppliedDto(unAppliedDto);
        innerResultDto.setAppliedDto(appliedDto);
        innerResultDto.setWeaponDto(weaponDto);
        return innerResultDto;
    }
}
