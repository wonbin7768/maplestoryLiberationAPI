package com.openapi.maplestory.liberation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionStatVo {
    private List<String> union_raider_stat = new ArrayList<>();
    private List<String> union_occupied_stat = new ArrayList<>();
}
