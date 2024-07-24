package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.BasicVo;
import com.openapi.maplestory.liberation.repository.dto.innerdto.BasicInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobCaseService {

    public BasicInfoDto caseFilter(BasicVo basicVo, String name) {
        BasicInfoDto basicInfoDto = new BasicInfoDto();
        String characterClass = basicVo.getCharacter_class();
        basicInfoDto.setCharacter_image(basicVo.getCharacter_image());
        switch (characterClass) {
            case "나이트워커":
            case "나이트로드":

                basicInfoDto.setWeapon("아대");
                basicInfoDto.setEtcPower(27.0);
                break;
            case "섀도어":
            case "듀얼블레이더":
                basicInfoDto.setWeapon("단검");
                break;
            case "팬텀":
                basicInfoDto.setWeapon("케인");
                break;
            case "카데나":
                basicInfoDto.setWeapon("체인");
                break;
            case "칼리":
                basicInfoDto.setWeapon("차크람");
                break;
            case "호영":
                basicInfoDto.setWeapon("부채");
                break;
            case "소울마스터":
                basicInfoDto.setWeapon("한손검");
                basicInfoDto.setWeapon("두손검");
                break;
            case "히어로":
                basicInfoDto.setWeapon("한손검");
                basicInfoDto.setWeapon("한손도끼");
                basicInfoDto.setWeapon("두손검");
                basicInfoDto.setWeapon("두손도끼");
                break;
            case "팔라딘":
                basicInfoDto.setWeapon("한손검");
                basicInfoDto.setWeapon("한손둔기");
                basicInfoDto.setWeapon("두손검");
                basicInfoDto.setWeapon("두손둔기");
                break;
            case "다크나이트":
                basicInfoDto.setWeapon("창");
                basicInfoDto.setWeapon("폴암");
                break;
            case "미하일":
                basicInfoDto.setWeapon("한손검");
                break;
            case "블래스터":
                basicInfoDto.setWeapon("건틀렛 리볼버");
                break;
            case "데몬슬레이어":
                basicInfoDto.setWeapon("한손도끼");
                basicInfoDto.setWeapon("한손둔기");
                break;
            case "아란":
                basicInfoDto.setWeapon("폴암");
                break;
            case "카이저":
                basicInfoDto.setWeapon("두손검");
                break;
            case "아델":
                basicInfoDto.setWeapon("튜너");
                break;
            case "제로":
                basicInfoDto.setWeapon("태도");
                break;
            case "은월":
            case "스트라이커":
            case "바이퍼":
            case "아크":
                basicInfoDto.setWeapon("너클");
                break;
            case "캐논슈터":
                basicInfoDto.setWeapon("핸드캐논");
                break;
            case "윈드브레이커":
            case "보우마스터":
                basicInfoDto.setWeapon("활");
                basicInfoDto.setEtcPower(9.0);
                break;
            case "와일드헌터":
            case "신궁":
                basicInfoDto.setWeapon("석궁");
                basicInfoDto.setEtcPower(9.0);
                break;
            case "패스파인더":
                basicInfoDto.setWeapon("에인션트 보우");
                break;
            case "메르세데스":
                basicInfoDto.setWeapon("듀얼 보우건");
                break;
            case "카인":
                basicInfoDto.setWeapon("브레스 슈터");
                break;
            case "캡틴":
                basicInfoDto.setWeapon("건");
                basicInfoDto.setEtcPower(22);
                break;
            case "메카닉":
                basicInfoDto.setWeapon("건");
                break;
            case "엔젤릭버스터":
                basicInfoDto.setWeapon("소울 슈터");
                break;
            case "플레임위자드":
            case "아크메이지(불,독)":
            case "아크메이지(썬,콜)":
            case "비숍":
            case "라라":
            case "에반":
                basicInfoDto.setWeapon("스태프");
                basicInfoDto.setWeapon("완드");
                break;
            case "배틀메이지":
                basicInfoDto.setWeapon("스태프");
                break;
            case "루미너스":
                basicInfoDto.setWeapon("샤이닝 로드");
                break;
            case "일리움":
                basicInfoDto.setWeapon("매직 건틀렛");
                break;
            case "키네시스":
                basicInfoDto.setWeapon("ESP 리미터");
                break;
            case "데몬어벤져":
                basicInfoDto.setWeapon("데스페라도");
                break;
            case "제논":
                basicInfoDto.setWeapon("에너지 소드");
                break;
            default:
                basicInfoDto.setJob("불가능");
                System.out.println("4차 전직 이전 캐릭터 입니다! 전직부터하세요!");
                break;
        }
        switch (characterClass) {
            case "나이트워커":
            case "나이트로드":
            case "섀도어":
            case "듀얼블레이더":
            case "팬텀":
            case "카데나":
            case "칼리":
            case "호영":
                System.out.println("characterClass = " + characterClass);
                basicInfoDto.setJob("럭덱");
                basicInfoDto.setMainStat("LUK");
                basicInfoDto.setSubStat("DEX");
                basicInfoDto.setMainPower("공격력");
                break;
            case "소울마스터":
            case "히어로":
            case "팔라딘":
            case "다크나이트":
            case "미하일":
            case "블래스터":
            case "데몬슬레이어":
            case "아란":
            case "카이저":
            case "아델":
            case "제로":
            case "바이퍼":
            case "캐논슈터":
            case "스트라이커":
            case "은월":
            case "아크":
                System.out.println("characterClass = " + characterClass);
                basicInfoDto.setJob("힘덱");
                basicInfoDto.setMainStat("STR");
                basicInfoDto.setSubStat("DEX");
                basicInfoDto.setMainPower("공격력");
                break;
            case "윈드브레이커":
            case "보우마스터":
            case "신궁":
            case "패스파인더":
            case "와일드헌터":
            case "메르세데스":
            case "카인":
            case "캡틴":
            case "메카닉":
            case "엔젤릭버스터":
                System.out.println("characterClass = " + characterClass);
                basicInfoDto.setJob("덱힘");
                basicInfoDto.setMainStat("DEX");
                basicInfoDto.setSubStat("STR");
                basicInfoDto.setMainPower("공격력");
                break;
            case "플레임위자드":
            case "아크메이지(불,독)":
            case "아크메이지(썬,콜)":
            case "비숍":
            case "배틀메이지":
            case "에반":
            case "루미너스":
            case "일리움":
            case "라라":
            case "키네시스":
                System.out.println("characterClass = " + characterClass);
                basicInfoDto.setJob("인럭");
                basicInfoDto.setMainStat("INT");
                basicInfoDto.setSubStat("LUK");
                basicInfoDto.setMainPower("마력");
                break;
            case "데몬어벤져":
            case "제논":
                basicInfoDto.setJob("데벤제논");
                System.out.println("데벤 제논 미구현");
                break;
            default:
                basicInfoDto.setJob("불가능");
                System.out.println("4차 전직 이전 캐릭터 입니다! 전직부터하세요!");
                break;
        }
        basicInfoDto.setNickName(name);
        return basicInfoDto;
    }

}
