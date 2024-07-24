package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.entity.ResultInfo;
import com.openapi.maplestory.liberation.repository.InnerResultDtoRepository;
import com.openapi.maplestory.liberation.repository.dto.innerdto.ResultInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrudResultInfoService {
    private final InnerResultDtoRepository innerResultDtoRepository;

    @Transactional
    public ResultInfo readResult(long id){
        ResultInfo innerResult = innerResultDtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id 가 없습니다 id = " + id));
        return innerResult;
    }
    @Transactional
    public long saveInnerResult(ResultInfoDto resultInfoDto) {
        ResultInfo entity = resultInfoDto.toEntity();
        ResultInfo resultInfo = innerResultDtoRepository.save(entity);
        Long id = resultInfo.getId();
        return id;
    }

    @Transactional
    public long updateSimpleLiberate(long id, ResultInfoDto result) {
        ResultInfo innerResult = innerResultDtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id 가 없습니다 id = " + id));
        ResultInfoDto resultInfoDto = new ResultInfoDto();
        resultInfoDto.updateSimpleLiberate(innerResult, result.getSimpleCombatPower());
        return id;
    }
}
