package com.openapi.maplestory.liberation.domain.dto.equipment.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetEquipmentVo {
    private PetEquipmentDetailVo pet_1_equipment;
    private PetEquipmentDetailVo pet_2_equipment;
    private PetEquipmentDetailVo pet_3_equipment;
}
