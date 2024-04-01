package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.dto.innerdto.AppliedDto;
import com.openapi.maplestory.liberation.domain.dto.innerdto.InnerResultDto;
import com.openapi.maplestory.liberation.domain.dto.innerdto.WeaponDto;
import com.openapi.maplestory.liberation.repository.InnerResultDtoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimpleLiberateService {
    private final InnerResultDtoRepository innerResultDtoRepository;
    @Transactional
    public long simpleLiberate(long id) {
        InnerResultDto innerResultDto = innerResultDtoRepository.findInnerResultDto(id);
        AppliedDto appliedDto = innerResultDto.getAppliedDto();
        String mainPower = innerResultDto.getInnerDto().getMainPower();
        WeaponDto weaponDto = innerResultDto.getWeaponDto();

        int totalAllStat = weaponDto.getTotalAllStat();
        int totalDamage = weaponDto.getTotalDamage();
        int totalMainStat = weaponDto.getTotalMainStat();
        int totalSubStat = weaponDto.getTotalSubStat();
        int totalMainPower = weaponDto.getTotalMainPower();

        int constantCorrection = weaponDto.getConstantCorrection();

        int allStatPercent = weaponDto.getAllStatPercent();
        int mainStatPercent = weaponDto.getMainStatPercent();
        int subStatPercent = weaponDto.getSubStatPercent();
        int powerPercent = weaponDto.getPowerPercent();
        int damagePercent = weaponDto.getDamagePercent();
        String soulOption = weaponDto.getSoulOption();


        int justAllStat = weaponDto.getJustAllStat();
        int justMainStat = weaponDto.getJustMainStat();
        int justSubStat = weaponDto.getJustSubStat();
        int justPowerStat = weaponDto.getJustPowerStat();

        int appliedDtoMainStat = appliedDto.getMainStat();
        int appliedDtoSubStat = appliedDto.getSubStat();
        int appliedDtoPower = appliedDto.getPower();
        int appliedDtoMainStatPercent = appliedDto.getMainStatPercent();
        int appliedDtoSubStatPercent = appliedDto.getSubStatPercent();
        int appliedDtoPowerPercent = appliedDto.getPowerPercent();
        double appliedDtoDamage = appliedDto.getDamage();

        int calMainStat = appliedDtoMainStat - (totalMainStat + justMainStat + justAllStat);
        int calSubStat = appliedDtoSubStat - (totalSubStat + justSubStat + justAllStat);
        int calPower = appliedDtoPower - (totalMainPower + justPowerStat + constantCorrection);
        int calMainStatPercent = appliedDtoMainStatPercent - (mainStatPercent + totalAllStat + allStatPercent);
        int calSubStatPercent = appliedDtoSubStatPercent - (subStatPercent + totalAllStat + allStatPercent);
        int calMainPowerPercent = appliedDtoPowerPercent - (powerPercent);
        double calDamagePercent = appliedDtoDamage - (totalDamage + damagePercent);

        weaponDto.setLiberation(1.1);
        appliedDto.setSimpleLiberate(1);
        appliedDto.setMainStat(calMainStat + 423);
        appliedDto.setSubStat(calSubStat + 325);
        appliedDto.setPower(calPower + 832);
        appliedDto.setMainStatPercent(calMainStatPercent);
        appliedDto.setSubStatPercent(calSubStatPercent);
        appliedDto.setPowerPercent(calMainPowerPercent + 51);
        appliedDto.setDamage(calDamagePercent + 80);

        innerResultDtoRepository.simpleLiberateUpdate(id,appliedDto,weaponDto);

        return id;
    }
}
