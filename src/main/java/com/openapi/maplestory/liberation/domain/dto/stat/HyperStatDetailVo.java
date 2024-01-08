package com.openapi.maplestory.liberation.domain.dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HyperStatDetailVo {
    private String stat_type;
    private int stat_point;
    private int stat_level;
    private String stat_increase;
}
