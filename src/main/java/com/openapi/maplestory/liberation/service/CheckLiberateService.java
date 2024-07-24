package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.equipment.ItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.ItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CheckLiberateService {
    public boolean checkLiberate(ItemVo itemVo) {
        List<ItemEquipmentVo> itemEquipment = itemVo.getItem_equipment();
        for (ItemEquipmentVo itemEquipmentVo : itemEquipment) {
            String itemName = itemEquipmentVo.getItem_name();
            System.out.println("itemName = " + itemName);
            if (itemName.contains("제네시스")) {
                return false;
            }
        }
        return true;
    }
}

