package com.openapi.maplestory.liberation.domain.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymbolVo {
    private List<SymbolEquipmentVo> symbol;
}
