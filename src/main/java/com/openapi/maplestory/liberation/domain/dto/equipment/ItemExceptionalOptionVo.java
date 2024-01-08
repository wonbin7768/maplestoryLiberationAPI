package com.openapi.maplestory.liberation.domain.dto.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemExceptionalOptionVo {
    private String str;
    private String dex;
    @JsonProperty("int")
    private String Int;
    private String luk;
    private String max_hp;
    private String attack_power;
    private String magic_power;
}
