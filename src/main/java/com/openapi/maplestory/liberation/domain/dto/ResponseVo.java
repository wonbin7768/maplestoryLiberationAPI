package com.openapi.maplestory.liberation.domain.dto;

import com.openapi.maplestory.liberation.domain.dto.equipment.ItemEquipmentVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseVo {

    String ucd;
    String message;
    List<ItemEquipmentVo> itemEquipmentVos;

    public ResponseVo() {

    }

}
