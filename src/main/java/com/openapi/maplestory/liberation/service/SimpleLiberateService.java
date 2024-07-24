package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.entity.ResultInfo;
import com.openapi.maplestory.liberation.repository.dto.innerdto.AppliedStatDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.ResultInfoDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.WeaponStatDto;
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
    public ResultInfoDto simpleLiberate(long id) {
        ResultInfo innerResult = innerResultDtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id 가 없습니다 id = " + id));
        ResultInfoDto resultInfoDto = innerResult.toResponseDto();
        System.out.println("resultInfoDtoSImpl = " + resultInfoDto);
        int simpleLiberate = resultInfoDto.getAppliedStatDto().getSimpleLiberate();
        if(simpleLiberate == 1){

        }
        AppliedStatDto appliedStatDto = resultInfoDto.getAppliedStatDto();
        String mainPower = resultInfoDto.getBasicInfoDto().getMainPower();
        WeaponStatDto weaponStatDto = resultInfoDto.getWeaponStatDto();

        int totalAllStat = weaponStatDto.getTotalAllStat();
        int totalDamage = weaponStatDto.getTotalDamage();
        int totalMainStat = weaponStatDto.getTotalMainStat();
        int totalSubStat = weaponStatDto.getTotalSubStat();
        int totalMainPower = weaponStatDto.getTotalMainPower();

        int constantCorrection = weaponStatDto.getConstantCorrection();

        int allStatPercent = weaponStatDto.getAllStatPercent();
        int mainStatPercent = weaponStatDto.getMainStatPercent();
        int subStatPercent = weaponStatDto.getSubStatPercent();
        int powerPercent = weaponStatDto.getPowerPercent();
        int damagePercent = weaponStatDto.getDamagePercent();
        String soulOption = weaponStatDto.getSoulOption();


        int justAllStat = weaponStatDto.getJustAllStat();
        int justMainStat = weaponStatDto.getJustMainStat();
        int justSubStat = weaponStatDto.getJustSubStat();
        int justPowerStat = weaponStatDto.getJustPowerStat();

        int appliedDtoMainStat = appliedStatDto.getMainStat();
        int appliedDtoSubStat = appliedStatDto.getSubStat();
        int appliedDtoPower = appliedStatDto.getPower();
        int appliedDtoMainStatPercent = appliedStatDto.getMainStatPercent();
        int appliedDtoSubStatPercent = appliedStatDto.getSubStatPercent();
        int appliedDtoPowerPercent = appliedStatDto.getPowerPercent();
        double appliedDtoDamage = appliedStatDto.getDamage();

        int calMainStat = appliedDtoMainStat - (totalMainStat + justMainStat + justAllStat);
        int calSubStat = appliedDtoSubStat - (totalSubStat + justSubStat + justAllStat);
        int calPower = appliedDtoPower - (totalMainPower + justPowerStat + constantCorrection);
        int calMainStatPercent = appliedDtoMainStatPercent - (mainStatPercent + totalAllStat + allStatPercent);
        int calSubStatPercent = appliedDtoSubStatPercent - (subStatPercent + totalAllStat + allStatPercent);
        int calMainPowerPercent = appliedDtoPowerPercent - (powerPercent);
        double calDamagePercent = appliedDtoDamage - (totalDamage + damagePercent);

        weaponStatDto.setLiberation(1.1);
        appliedStatDto.setSimpleLiberate(1);
        appliedStatDto.setMainStat(calMainStat + 423);
        appliedStatDto.setSubStat(calSubStat + 325);
        appliedStatDto.setPower(calPower + 832);
        appliedStatDto.setMainStatPercent(calMainStatPercent);
        appliedStatDto.setSubStatPercent(calSubStatPercent);
        appliedStatDto.setPowerPercent(calMainPowerPercent + 51);
        appliedStatDto.setDamage(calDamagePercent + 80);

        return resultInfoDto;
    }
}
