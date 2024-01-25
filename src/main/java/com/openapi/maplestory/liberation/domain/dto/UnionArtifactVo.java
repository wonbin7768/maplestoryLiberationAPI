package com.openapi.maplestory.liberation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionArtifactVo {
    String date;
    private List<UnionArtifactEffectVo> union_artifact_effect = new ArrayList<>();
    private List<UnionArtifactCrystalVo> union_artifact_crystal = new ArrayList<>();
}
