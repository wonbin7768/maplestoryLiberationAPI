package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetEffectInfoVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetEffectVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.repository.dto.innerdto.AppliedStatDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.ChangeSetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CalChangeSetService {
    public ChangeSetDto calChangeSet(AppliedStatDto appliedStatDto , SetVo setVo , String weaponName){

        ChangeSetDto changeSetDto = new ChangeSetDto();

        //셋트옵션 스탯 구하기
        int calSetStat = 0;
        int calSetMainPower = 0;
        int calSetSubStat = 0;
        double calSetDamage = 0.0;
        double calSetCriDamage = 0.0;
        String setName = "";
        int setCount = 0;
        Map<String,Integer> setMap = new HashMap<String, Integer>();

        List<SetEffectVo> setEffect = setVo.getSet_effect();
        for (SetEffectVo setEffectVo : setEffect) {
            List<SetEffectInfoVo> setEffectInfoVo = setEffectVo.getSetEffectInfoVo();
            setName = setEffectVo.getSet_name();
            for (SetEffectInfoVo effectInfoVo : setEffectInfoVo) {
                setCount = effectInfoVo.getSet_count();
                setMap.put(setName,setCount);
            }
        }
        System.out.println("setMap = " + setMap);
        for (String key : setMap.keySet()) {
            if (key.contains("에테르넬")) {
                if (setMap.get(key) == 1){
                    calSetMainPower += 40;
                    calSetDamage += 10.0;
                } else if (setMap.get(key) == 2) {
                    calSetStat += 50;
                    calSetSubStat += 50;
                    calSetMainPower += 40;
                    calSetDamage += 10.0;
                } else if (setMap.get(key) == 3) {
                    calSetMainPower += 40;
                    calSetDamage += 10.0;
                } else if (setMap.get(key) == 4) {
                    calSetMainPower += 40;
                } else if (setMap.get(key) == 5) {
                    calSetMainPower += 40;
                    calSetDamage += 30.0;
                } else if (setMap.get(key) == 6) {
                    calSetStat += 50;
                    calSetSubStat += 50;
                    calSetMainPower += 40;
                    calSetDamage += 10.0;
                } else if (setMap.get(key) == 7) {
                    calSetMainPower += 40;
                    calSetDamage += 10.0;
                }
            }
            if (key.contains("마이스터")) {
                if (setMap.get(key) >= 3){
                    calSetDamage += 20.0;
                }
            }
            if(weaponName.contains("파프니르")){
                if (key.contains("루타비스")){
                    if(setMap.get(key) == 3){
                       calSetMainPower += -50;
                    } else if (setMap.get(key) == 2) {
                        calSetStat += -20;
                        calSetSubStat += -20;
                    }
                } else if (key.contains("앱솔랩스")) {
                    if (setMap.get(key) == 3){
                        calSetMainPower += 20;
                    }else if (setMap.get(key) == 4){
                        calSetMainPower += 30;
                        calSetDamage += 10.0;
                    }else if (setMap.get(key) == 5){
                        calSetMainPower += 20;
                    }else if (setMap.get(key) == 6){
                        calSetMainPower += 20;
                    }
                } else if (key.contains("아케인셰이드")) {
                    if (setMap.get(key) == 3){
                        calSetMainPower += 35;
                        calSetStat += 50;
                        calSetSubStat += 50;
                        calSetDamage += 10;
                    }else if (setMap.get(key) == 4){
                        calSetMainPower += 40;
                        calSetDamage += 10.0;
                    }else if (setMap.get(key) == 5){
                        calSetMainPower += 30;
                    }else if (setMap.get(key) == 6){
                        calSetMainPower += 30;
                    }
                }
            } else if (weaponName.contains("앱솔랩스")) {
                if (key.contains("루타비스")){
                    if(setMap.get(key) == 3){
                        calSetDamage += 30;
                    }
                } else if (key.contains("앱솔랩스")) {
                    if (setMap.get(key) == 3){
                        calSetStat += -30;
                        calSetSubStat += -30;
                        calSetMainPower += -20;
                        calSetDamage += -10;
                    }else if (setMap.get(key) == 2){
                        calSetMainPower += -20;
                        calSetDamage += -10;
                    }
                } else if (key.contains("아케인셰이드")) {
                    if (setMap.get(key) == 3){
                        calSetMainPower += 35;
                        calSetStat += 50;
                        calSetSubStat += 50;
                        calSetDamage += 10;
                    }else if (setMap.get(key) == 4){
                        calSetMainPower += 40;
                        calSetDamage += 10.0;
                    }else if (setMap.get(key) == 5){
                        calSetMainPower += 30;
                    }else if (setMap.get(key) == 6){
                        calSetMainPower += 30;
                    }
                }
            } else if (weaponName.contains("아케인셰이드")){
                    if (key.contains("루타비스")){
                        if(setMap.get(key) == 3){
                            calSetDamage += 30;
                        }
                    } else if (key.contains("앱솔랩스")) {
                        if (setMap.get(key) == 3){
                            calSetMainPower += 20;
                        }else if (setMap.get(key) == 4){
                            calSetMainPower += 25;
                            calSetDamage += 10.0;
                        }else if (setMap.get(key) == 5){
                            calSetMainPower += 20;
                        }else if (setMap.get(key) == 6){
                            calSetMainPower += 20;
                        }
                    } else if (key.contains("아케인셰이드")) {
                        if (setMap.get(key) == 3){
                            calSetMainPower += -30;
                        }else if (setMap.get(key) == 2){
                            calSetMainPower += -30;
                            calSetDamage += -10;
                        }
                    }
            }
        }
        changeSetDto.setPower(calSetMainPower);
        changeSetDto.setDamage(calSetDamage);
        changeSetDto.setCriDamage(calSetCriDamage);
        changeSetDto.setMainStat(calSetStat);
        changeSetDto.setSubStat(calSetSubStat);

        System.out.println("changeSetDto = " + changeSetDto);
        System.out.println("appliedStatDto = " + appliedStatDto);

        return changeSetDto;
    }

}
