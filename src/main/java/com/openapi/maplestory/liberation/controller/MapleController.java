package com.openapi.maplestory.liberation.controller;

import com.openapi.maplestory.liberation.domain.BasicVo;
import com.openapi.maplestory.liberation.domain.CharacterSkillVo;
import com.openapi.maplestory.liberation.domain.UnionArtifactVo;
import com.openapi.maplestory.liberation.domain.UnionStatVo;
import com.openapi.maplestory.liberation.domain.entity.ResultInfo;
import com.openapi.maplestory.liberation.domain.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.stat.AbilityVo;
import com.openapi.maplestory.liberation.domain.stat.HexaStatVo;
import com.openapi.maplestory.liberation.domain.stat.HyperStatVo;
import com.openapi.maplestory.liberation.domain.stat.StatVo;
import com.openapi.maplestory.liberation.repository.dto.MapleRequestDto;
import com.openapi.maplestory.liberation.repository.dto.MessageDto;
import com.openapi.maplestory.liberation.repository.dto.innerdto.*;
import com.openapi.maplestory.liberation.service.*;
import com.openapi.maplestory.liberation.util.DateUtil;
import com.openapi.maplestory.liberation.util.combatUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class MapleController {

    private final MapleService mapleService;
    private final CalculateService calculateService;
    private final JobCaseService jobCaseService;
    private final CalWeaponService calWeaponService;
    private final CalCombatPowerService calCombatPowerService;
    private final CrudResultInfoService crudResultInfoService;
    private final CheckLiberateService checkLiberateService;
    private final CalChangeSetService calChangeSetService;

    @GetMapping("/mapleLiberation")
    public String getNickName() {
        return "index";
    }

    @GetMapping("/getMapleInfo")
    public String getMapleInfo(
            @RequestParam("name")
            @NotBlank
            @Pattern(
                    message = "닉네임 양식을 맞춰주세요",
                    regexp = "^[가-힣]{2,6}$|^[a-zA-Z0-9]{4,13}$|^[가-힣a-zA-Z0-9]{2,6}$"
            ) String name,
            Model model, RedirectAttributes redirectAttributes) throws ParseException {

        MapleRequestDto mapleRequestDto = new MapleRequestDto();
        mapleRequestDto.setApikey("test_20d5892d17fbffcdbb4b6c8d34a5943124807b95b4f783779eea66850ce570b207887955e11ecb37b3264b7471d00004");
        mapleRequestDto.setBaseUrl("http://open.api.nexon.com/maplestory");
        mapleRequestDto.setApiUrl("/v1/id?character_name={name}");
        mapleRequestDto.setName(name);
        System.out.println("name = " + name);
        if (Objects.equals(name, "")) {
            MessageDto message = new MessageDto("닉네임을 입력하세요!", "/mapleLiberation", RequestMethod.GET);
            return showMessageAndRedirect(message, model);
        }
        String ocid = mapleService.getOcid(mapleRequestDto);
        System.out.println("ocid = " + ocid);

        mapleRequestDto.setOcid(ocid);
        mapleRequestDto.setDate(DateUtil.getDate());
        mapleRequestDto.setApiUrl("/v1/character/basic?ocid={mapleRequestDto.getOcid()}");
        BasicVo basicVo = mapleService.getBasic(mapleRequestDto);
        String liberationQuestClearFlag = basicVo.getLiberation_quest_clear_flag();
        if(liberationQuestClearFlag.contains("true")){
            MessageDto message = new MessageDto("이미 해방한 유저입니다!", "/mapleLiberation", RequestMethod.GET);
            return showMessageAndRedirect(message, model);
        }
        mapleRequestDto.setApiUrl("/v1/character/stat?ocid={mapleRequestDto.getOcid()}");
        StatVo statVo = mapleService.getStat(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/hyper-stat?ocid={mapleRequestDto.getOcid()}");
        HyperStatVo hyperStatVo = mapleService.getHyperStat(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/item-equipment?ocid={mapleRequestDto.getOcid()}");
        ItemVo itemVo = mapleService.getItem(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/ability?ocid={mapleRequestDto.getOcid()}");
        AbilityVo abilityVo = mapleService.getAbilityVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/cashitem-equipment?ocid={mapleRequestDto.getOcid()}");
        CashItemEquipmentVo cashItemEquipmentVo = mapleService.getCashItemVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/symbol-equipment?ocid={mapleRequestDto.getOcid()}");
        SymbolVo symbolVo = mapleService.getSymbolVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/set-effect?ocid={mapleRequestDto.getOcid()}");
        SetVo setVo = mapleService.getSetVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/pet-equipment?ocid={mapleRequestDto.getOcid()}");
        PetEquipmentVo petEquipmentVo = mapleService.getPetVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/skill?ocid={mapleRequestDto.getOcid()}&character_skill_grade=0");
        CharacterSkillVo skillVo = mapleService.getSkillVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/character/hexamatrix-stat?ocid={mapleRequestDto.getOcid()}");
        HexaStatVo hexaStatVo = mapleService.getHexaVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/user/union-raider?ocid={mapleRequestDto.getOcid()}");
        UnionStatVo unionStatVo = mapleService.getUnionVo(mapleRequestDto);
        mapleRequestDto.setApiUrl("/v1/user/union-artifact?ocid={mapleRequestDto.getOcid()}");
        UnionArtifactVo unionArtifactVo = mapleService.getUnionArtifactVo(mapleRequestDto);

        BasicInfoDto basicInfoDto = jobCaseService.caseFilter(basicVo, name);
        String job = basicInfoDto.getJob();
        if (Objects.equals(job, "불가능")) {
            MessageDto message = new MessageDto("4차 이전 캐릭이거나 api 등록되지 않은 캐릭터입니다!", "/mapleLiberation", RequestMethod.GET);
            return showMessageAndRedirect(message, model);
        } else if (Objects.equals(job, "데벤제논")) {
            MessageDto message = new MessageDto("데벤 제논 미구현 입니다. ㅠ", "/mapleLiberation", RequestMethod.GET);
            model.addAttribute("msg", "데벤 제논 미구현 입니다. ㅠ");
            model.addAttribute("url", "/getMapleInfo");
            return showMessageAndRedirect(message, model);
        }
        UnAppliedStatDto unAppliedStatDto = calculateService.calUnAppliedStat(basicInfoDto, basicVo, hyperStatVo, symbolVo, unionStatVo, hexaStatVo);
        AppliedStatDto appliedStatDto = calculateService.calAppliedStat(basicInfoDto, basicVo, statVo, itemVo, cashItemEquipmentVo, abilityVo, setVo, skillVo, mapleRequestDto.getDate(), unionStatVo, unionArtifactVo, petEquipmentVo);
        WeaponStatDto weaponStatDto = calWeaponService.calWeapon(basicInfoDto, itemVo, basicVo);
        String weaponName = weaponStatDto.getWeaponName();
        ChangeSetDto changeSetDto = calChangeSetService.calChangeSet(appliedStatDto, setVo, weaponName);
        ResultInfoDto resultInfoDto = calCombatPowerService.calCombatPower(basicInfoDto, unAppliedStatDto, appliedStatDto, weaponStatDto , changeSetDto);

        long id = crudResultInfoService.saveInnerResult(resultInfoDto);

        redirectAttributes.addAttribute("id",id);
        return "redirect:selectHero/{id}";
    }
    @GetMapping("/selectHero/{id}")
    public String findMapleInfo(@PathVariable("id") String id, Model model){
        ResultInfo resultInfo = crudResultInfoService.readResult(Long.parseLong(id));
        String characterImage = resultInfo.getBasicInfo().getCharacter_image();
        String nickName = resultInfo.getBasicInfo().getNickName();
        long combatPower = resultInfo.getCombatPower();
        String transCombat = combatUtil.transCombatType(combatPower);

        model.addAttribute("id", id);
        model.addAttribute("img", characterImage);
        model.addAttribute("nickName", nickName);
        model.addAttribute("combatPower", transCombat);

        return "selectHero";
    }



    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        System.out.println("params = " + params.getMessage());
        return "common/messageRedirect";
    }

}
