package com.openapi.maplestory.liberation.domain.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymbolEquipmentVo {
    private String symbol_str;
    private String symbol_dex;
    private String symbol_int;
    private String symbol_luk;
    private String symbol_hp;
}
