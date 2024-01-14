package com.openapi.maplestory.liberation.domain.dto.equipment.seteffect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetVo {
    private List<SetEffectVo> set_effect = new ArrayList<>();
}
