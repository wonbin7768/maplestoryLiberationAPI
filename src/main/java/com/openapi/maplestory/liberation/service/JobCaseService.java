package com.openapi.maplestory.liberation.service;

import com.openapi.maplestory.liberation.domain.dto.BasicVo;
import com.openapi.maplestory.liberation.domain.dto.innerdto.InnerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobCaseService {

    public InnerDto caseFilter(BasicVo basicVo, String name) {
        InnerDto innerDto = new InnerDto();
        String characterClass = basicVo.getCharacter_class();
        switch (characterClass) {
            case "나이트워커":
            case "나이트로드":
                innerDto.setWeapon("아대");
                innerDto.setEtcPower(27.0);
                break;
            case "섀도어":
            case "듀얼블레이더":
                innerDto.setWeapon("단검");
                break;
            case "팬텀":
                innerDto.setWeapon("케인");
                break;
            case "카데나":
                innerDto.setWeapon("체인");
                break;
            case "칼리":
                innerDto.setWeapon("차크람");
                break;
            case "호영":
                innerDto.setWeapon("부채");
                break;
            case "소울마스터":
                innerDto.setWeapon("한손검");
                innerDto.setWeapon("두손검");
                break;
            case "히어로":
                innerDto.setWeapon("한손검");
                innerDto.setWeapon("한손도끼");
                innerDto.setWeapon("두손검");
                innerDto.setWeapon("두손도끼");
                break;
            case "팔라딘":
                innerDto.setWeapon("한손검");
                innerDto.setWeapon("한손둔기");
                innerDto.setWeapon("두손검");
                innerDto.setWeapon("두손둔기");
                break;
            case "다크나이트":
                innerDto.setWeapon("창");
                innerDto.setWeapon("폴암");
                break;
            case "미하일":
                innerDto.setWeapon("한손검");
                break;
            case "블래스터":
                innerDto.setWeapon("건틀렛 리볼버");
                break;
            case "데몬슬레이어":
                innerDto.setWeapon("한손도끼");
                innerDto.setWeapon("한손둔기");
                break;
            case "아란":
                innerDto.setWeapon("폴암");
                break;
            case "카이저":
                innerDto.setWeapon("두손검");
                break;
            case "아델":
                innerDto.setWeapon("튜너");
                break;
            case "제로":
                innerDto.setWeapon("대검");
                innerDto.setWeapon("태도");
                break;
            case "은월":
            case "스트라이커":
            case "바이퍼":
            case "아크":
                innerDto.setWeapon("너클");
                break;
            case "캐논슈터":
                innerDto.setWeapon("핸드캐논");
                break;
            case "윈드브레이커":
            case "보우마스터":
                innerDto.setWeapon("활");
                innerDto.setEtcPower(9.0);
                break;
            case "와일드헌터":
            case "신궁":
                innerDto.setWeapon("석궁");
                innerDto.setEtcPower(9.0);
                break;
            case "패스파인더":
                innerDto.setWeapon("에인션트 보우");
                break;
            case "메르세데스":
                innerDto.setWeapon("듀얼 보우건");
                break;
            case "카인":
                innerDto.setWeapon("브레스 슈터");
                break;
            case "캡틴":
                innerDto.setWeapon("건");
                innerDto.setEtcPower(22);
                break;
            case "메카닉":
                innerDto.setWeapon("건");
                break;
            case "엔젤릭버스터":
                innerDto.setWeapon("소울 슈터");
                break;
            case "플레임위자드":
            case "아크메이지(불,독)":
            case "아크메이지(썬,콜)":
            case "비숍":
            case "라라":
            case "에반":
                innerDto.setWeapon("스태프");
                innerDto.setWeapon("완드");
                break;
            case "배틀메이지":
                innerDto.setWeapon("스태프");
                break;
            case "루미너스":
                innerDto.setWeapon("샤이닝 로드");
                break;
            case "일리움":
                innerDto.setWeapon("매직 건틀렛");
                break;
            case "키네시스":
                innerDto.setWeapon("ESP 리미터");
                break;
            case "데몬어벤져":
                innerDto.setWeapon("데스페라도");
                break;
            case "제논":
                innerDto.setWeapon("에너지 소드");
                break;
            default:
                innerDto.setJob("불가능");
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
                innerDto.setJob("럭덱");
                innerDto.setMainStat("LUK");
                innerDto.setSubStat("DEX");
                innerDto.setMainPower("공격력");
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
                innerDto.setJob("힘덱");
                innerDto.setMainStat("STR");
                innerDto.setSubStat("DEX");
                innerDto.setMainPower("공격력");
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
                innerDto.setJob("덱힘");
                innerDto.setMainStat("DEX");
                innerDto.setSubStat("STR");
                innerDto.setMainPower("공격력");
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
                innerDto.setJob("인럭");
                innerDto.setMainStat("INT");
                innerDto.setSubStat("LUK");
                innerDto.setMainPower("마력");
                break;
            case "데몬어벤져":
            case "제논":
                innerDto.setJob("데벤제논");
                System.out.println("데벤 제논 미구현");
                break;
            default:
                innerDto.setJob("불가능");
                System.out.println("4차 전직 이전 캐릭터 입니다! 전직부터하세요!");
                break;
        }
        innerDto.setNickName(name);
        return innerDto;
    }

}
