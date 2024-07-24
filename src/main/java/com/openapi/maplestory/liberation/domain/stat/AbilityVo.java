package com.openapi.maplestory.liberation.domain.stat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityVo {
    @JsonProperty("ability_info")
    private List<AbilityInfo> ability_info = new ArrayList<>();
}
