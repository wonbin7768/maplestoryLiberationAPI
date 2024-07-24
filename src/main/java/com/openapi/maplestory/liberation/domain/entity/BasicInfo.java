package com.openapi.maplestory.liberation.domain.entity;

import com.openapi.maplestory.liberation.repository.dto.innerdto.BasicInfoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BasicInfo {
    @Id
    @GeneratedValue
    @Column(name = "innerdto_id")
    private Long id;
    private String nickName;
    private String job;
    private String mainStat;
    private String subStat;
    private String mainPower;
    private List<String> weapon = new ArrayList<>();
    private double etcPower;
    @Column(length = 500)
    private String character_image;

    @Builder
    private BasicInfo(String nickName , String job , String mainStat ,String subStat ,String mainPower , List<String> weapon ,double etcPower ,String character_image){
        this.nickName = nickName;
        this.job = job;
        this.mainStat = mainStat;
        this.subStat = subStat;
        this.mainPower = mainPower;
        this.weapon = weapon;
        this.etcPower = etcPower;
        this.character_image = character_image;
    }
    public BasicInfoDto toResponseDto(){
        return BasicInfoDto.builder()
                .id(id)
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

    @Override
    public String toString() {
        return "BasicInfo{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", job='" + job + '\'' +
                ", mainStat='" + mainStat + '\'' +
                ", subStat='" + subStat + '\'' +
                ", mainPower='" + mainPower + '\'' +
                ", weapon=" + weapon +
                ", etcPower=" + etcPower +
                '}';
    }
}
