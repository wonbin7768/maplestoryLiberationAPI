package com.openapi.maplestory.liberation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillVo {
    private String skill_name;
    private int skill_level;
    private String skill_effect;
}
