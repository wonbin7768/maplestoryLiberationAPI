package com.openapi.maplestory.liberation.domain.dto.stat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatVo {
    private String character_class;
    @JsonProperty("final_stat")
    private List<FinalStatVo> final_stat = new ArrayList<>();
}
