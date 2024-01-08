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

    private int preset_no;
    @JsonProperty("cash_item_equipment_preset_1")
    private List<CashItemPresetVo> cash_item_equipment_preset_1 = new ArrayList<>();
    @JsonProperty("cash_item_equipment_preset_2")
    private List<CashItemPresetVo> cash_item_equipment_preset_2 = new ArrayList<>();
    @JsonProperty("cash_item_equipment_preset_3")
    private List<CashItemPresetVo> cash_item_equipment_preset_3 = new ArrayList<>();

}
