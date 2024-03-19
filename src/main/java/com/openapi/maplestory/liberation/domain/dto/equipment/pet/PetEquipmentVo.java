package com.openapi.maplestory.liberation.domain.dto.equipment.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetEquipmentVo {
    private String pet_1_name;
    private String pet_2_name;
    private String pet_3_name;
    private PetEquipmentDetailVo pet_1_equipment;
    private PetEquipmentDetailVo pet_2_equipment;
    private PetEquipmentDetailVo pet_3_equipment;
}
