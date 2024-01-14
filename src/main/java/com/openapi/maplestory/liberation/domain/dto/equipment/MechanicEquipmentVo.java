package com.openapi.maplestory.liberation.domain.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicEquipmentVo {
    private String item_equipment_part;
    private String equipment_slot;
    private String item_name;
    private String item_description;
    private ItemTotalOptionVo item_total_option;
}
