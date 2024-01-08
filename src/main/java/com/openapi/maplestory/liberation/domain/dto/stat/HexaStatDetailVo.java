package com.openapi.maplestory.liberation.domain.dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HexaStatDetailVo {
    private String slot_id;
    private String main_stat_name;
    private String sub_stat_name_1;
    private String sub_stat_name_2;
    private int main_stat_level;
    private int sub_stat_level_1;
    private int sub_stat_level_2;
    private int stat_grade;
}
