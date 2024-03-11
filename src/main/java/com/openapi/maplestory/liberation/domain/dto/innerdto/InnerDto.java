package com.openapi.maplestory.liberation.domain.dto.innerdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InnerDto {

    private String job;
    private String MainStat;
    private String SubStat;
    private String MainPower;
    private List<String> Weapon = new ArrayList<>();

    public void setWeapon(String weapons) {
        Weapon.add(weapons);
    }
}
