package com.openapi.maplestory.liberation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
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
    private CharacterHyperStatVo characterHyperStatVo;
}
