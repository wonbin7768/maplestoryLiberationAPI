package com.openapi.maplestory.liberation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.ItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetEffectVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HexaStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HyperStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.FinalStatVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapleResponseVo {
    private String ocid;
    private String date;
    private String character_class;
    @JsonProperty("final_stat")
    private List<FinalStatVo> final_Stat = new ArrayList<>();
    @JsonUnwrapped
    private HyperStatVo hyperStat;
    @JsonProperty("item_equipment")
    private List<ItemEquipmentVo> itemEquipmentVo = new ArrayList<>();
    @JsonProperty("ability_info")
    private List<AbilityVo> abilityVo = new ArrayList<>();
    @JsonUnwrapped
    private CashItemEquipmentVo cashItemEquipment;
    @JsonProperty("symbol")
    private List<SymbolEquipmentVo> symbolEquipment = new ArrayList<>();
    @JsonProperty("set_effect")
    private List<SetEffectVo> setEffectVo = new ArrayList<>();
    @JsonUnwrapped
    private PetEquipmentVo petEquipment;
    @JsonUnwrapped
    private CharacterSkillVo characterSkillVo;
    @JsonUnwrapped
    private HexaStatVo hexaStatVo;
    @JsonUnwrapped
    private UnionStatVo unionStatVo;
}
