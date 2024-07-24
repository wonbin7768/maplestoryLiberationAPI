package com.openapi.maplestory.liberation.domain.stat;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalStatVo {
    @JsonProperty("stat_name")
    private String stat_name;
    @JsonProperty("stat_value")
    private String stat_value;
}
