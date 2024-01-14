package com.openapi.maplestory.liberation.domain.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {
    private List<ItemEquipmentVo> item_equipment = new ArrayList<>();
    private Title title;
    private List<DragonEquipmentVo> dragon_equipment = new ArrayList<>();
    private List<MechanicEquipmentVo> mechanic_equipment;

}
