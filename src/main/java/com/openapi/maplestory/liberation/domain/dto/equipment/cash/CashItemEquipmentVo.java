package com.openapi.maplestory.liberation.domain.dto.equipment.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashItemEquipmentVo {
    @JsonProperty("cash_item_equipment_base")
    private List<CashItemBaseVo> cash_item_equipment_base = new ArrayList<>();
}
