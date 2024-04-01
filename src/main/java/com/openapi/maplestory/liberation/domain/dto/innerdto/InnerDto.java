package com.openapi.maplestory.liberation.domain.dto.innerdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InnerDto {
    @Id @GeneratedValue
    @Column(name = "innerdto_id")
    private Long id;
    private String nickName;
    private String job;
    private String MainStat;
    private String SubStat;
    private String MainPower;
    private List<String> Weapon = new ArrayList<>();
    private double etcPower;

    public void setWeapon(String weapons) {
        Weapon.add(weapons);
    }
}
