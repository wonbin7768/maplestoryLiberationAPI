package com.openapi.maplestory.liberation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionArtifactCrystalVo {
    String name;
    String validity_flag;
    String date_expire;
    String level;
    String crystal_option_name_1;
    String crystal_option_name_2;
    String crystal_option_name_3;
}
