package com.openapi.maplestory.liberation.domain.dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HyperStatVo {

    private String character_class;
    private String use_preset_no;
    private List<HyperStatDetailVo> hyper_stat_preset_1 = new ArrayList<>();
    private List<HyperStatDetailVo> hyper_stat_preset_2 = new ArrayList<>();
    private List<HyperStatDetailVo> hyper_stat_preset_3 = new ArrayList<>();

}
