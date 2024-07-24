package com.openapi.maplestory.liberation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSkillVo {
    private String character_skill_grade;
    @JsonProperty("character_skill")
    private List<SkillVo> skillVo;
}
