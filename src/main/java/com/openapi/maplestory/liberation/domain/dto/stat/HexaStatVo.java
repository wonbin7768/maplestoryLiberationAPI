package com.openapi.maplestory.liberation.domain.dto.stat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HexaStatVo {
    @JsonProperty("character_hexa_stat_core")
    private List<HexaStatDetailVo> hexaStatDetailVo = new ArrayList<>();
    @JsonProperty("preset_hexa_stat_core")
    private List<HexaStatDetailVo> presetHexaStatDetailVo = new ArrayList<>();

}
