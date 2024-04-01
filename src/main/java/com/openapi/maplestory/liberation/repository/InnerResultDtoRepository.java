package com.openapi.maplestory.liberation.repository;

import com.openapi.maplestory.liberation.domain.dto.innerdto.AppliedDto;
import com.openapi.maplestory.liberation.domain.dto.innerdto.InnerResultDto;
import com.openapi.maplestory.liberation.domain.dto.innerdto.WeaponDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InnerResultDtoRepository {

    private final EntityManager em;
    public Long save(InnerResultDto innerResultDto){
        em.persist(innerResultDto);
        return innerResultDto.getId();
    }
    public InnerResultDto findInnerResultDto (Long id ){
        return em.find(InnerResultDto.class , id);
    }
    public void simpleLiberateUpdate(Long id , AppliedDto appliedDto, WeaponDto weaponDto){
        InnerResultDto innerResultDto = findInnerResultDto(id);
        innerResultDto.setAppliedDto(appliedDto);
        innerResultDto.setWeaponDto(weaponDto);
    }

    public void combatPowerUpdate(Long id, InnerResultDto innerResultDto) {
        InnerResultDto innerResultDto1 = findInnerResultDto(id);
        innerResultDto1.setCombatPower(innerResultDto.getCombatPower());
    }
}
