package com.openapi.maplestory.liberation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicVo {
    private String character_name;
    private String world_name;
    private String character_class;
    private int character_level;
    private String character_image;
    private String liberation_quest_clear_flag;
}
