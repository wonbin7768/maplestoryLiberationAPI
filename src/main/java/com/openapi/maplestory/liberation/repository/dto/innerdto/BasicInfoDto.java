package com.openapi.maplestory.liberation.repository.dto.innerdto;

import com.openapi.maplestory.liberation.domain.entity.BasicInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicInfoDto {
    private Long id;
    private String nickName;
    private String job;
    private String mainStat;
    private String subStat;
    private String mainPower;
    private List<String> weapon = new ArrayList<>();
    private double etcPower;
    private String character_image;

    public void setWeapon(String weapons) {
        weapon.add(weapons);
    }
    public BasicInfo toEntity(){
        return BasicInfo.builder()
                .nickName(nickName)
                .job(job)
                .mainStat(mainStat)
                .subStat(subStat)
                .mainPower(mainPower)
                .weapon(weapon)
                .etcPower(etcPower)
                .character_image(character_image)
                .build();
    }
}
