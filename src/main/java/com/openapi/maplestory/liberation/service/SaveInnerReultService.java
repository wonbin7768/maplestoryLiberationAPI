package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.dto.innerdto.*;
import com.openapi.maplestory.liberation.repository.InnerResultDtoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaveInnerReultService {
    private final InnerResultDtoRepository innerResultDtoRepository;

    @Transactional
    public long saveInnerResult(InnerDto innerDto, UnAppliedDto unAppliedDto, AppliedDto appliedDto, WeaponDto weaponDto) {
        InnerResultDto result = InnerResultDto.createResult(innerDto, unAppliedDto, appliedDto, weaponDto);
        Long saveId = innerResultDtoRepository.save(result);
        return saveId;
    }
}
