package com.openapi.maplestory.liberation.domain.dto.equipment.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetEquipmentDetailVo {
    private List<ItemOption> item_option;
}
