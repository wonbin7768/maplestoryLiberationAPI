package com.openapi.maplestory.liberation.domain.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEquipmentVo {
    private String item_equipment_part;
    private String equipment_slot;
    private String item_name;
    private String item_icon;
    private String item_shape_name;
    private String item_shape_icon;
    @JsonProperty("item_total_option")
    private ItemTotalOptionVo itemTotalOption;
    private String potential_option_1;
    private String potential_option_2;
    private String potential_option_3;
    private String additional_potential_option_1;
    private String additional_potential_option_2;
    private String additional_potential_option_3;
    @JsonProperty("item_exceptional_option")
    private ItemExceptionalOptionVo item_exceptional_option;
    private String soul_name;
    private String soul_option;
    private String starforce;
    private String scroll_upgrade;
    private String starforce_scroll_flag;
    private String special_ring_level;
    @JsonProperty("item_add_option")
    private ItemAddOptionVo item_add_option;
    @JsonProperty("item_base_option")
    private ItemBaseOptionVo item_base_option;
    @JsonProperty("item_etc_option")
    private ItemEtcOptionVo item_etc_option;
    @JsonProperty("item_starforce_option")
    private ItemStarforceOptionVo item_starforce_option;
}
