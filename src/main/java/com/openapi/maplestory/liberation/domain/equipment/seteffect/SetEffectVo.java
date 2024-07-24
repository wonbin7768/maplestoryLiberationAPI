package com.openapi.maplestory.liberation.domain.equipment.seteffect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetEffectVo {
    private String set_name;
    private String total_set_count;
    @JsonProperty("set_effect_info")
    private List<SetEffectInfoVo> setEffectInfoVo = new ArrayList<>();
}
