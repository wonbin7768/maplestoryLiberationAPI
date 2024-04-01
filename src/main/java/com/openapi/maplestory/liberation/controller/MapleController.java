package com.openapi.maplestory.liberation.controller;

import com.openapi.maplestory.liberation.domain.dto.*;
import com.openapi.maplestory.liberation.domain.dto.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.dto.innerdto.*;
import com.openapi.maplestory.liberation.domain.dto.stat.AbilityVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HexaStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HyperStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.StatVo;
import com.openapi.maplestory.liberation.service.*;
import com.openapi.maplestory.liberation.util.DateUtil;
import com.openapi.maplestory.liberation.util.combatUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapleController {

    private final MapleService mapleService;
    private final CalculateService calculateService;
    private final JobCaseService jobCaseService;
    private final CalWeaponService calWeaponService;
    private final CalCombatPowerService calCombatPowerService;
    private final SaveInnerReultService saveInnerReultService;

    @GetMapping("/mapleLiberation")
    public String getNickName(){
        return "index";
    }
    @GetMapping("/getMapleInfo")
    public String getMapleInfo(@RequestParam  (value = "name") @Valid @NotBlank String name, Model model) throws ParseException {
        MapleRequestVo mapleRequestVo = new MapleRequestVo();
        mapleRequestVo.setApikey("test_20d5892d17fbffcdbb4b6c8d34a5943124807b95b4f783779eea66850ce570b207887955e11ecb37b3264b7471d00004");
        mapleRequestVo.setBaseUrl("http://open.api.nexon.com/maplestory");
        mapleRequestVo.setApiUrl("/v1/id?character_name={name}");
        mapleRequestVo.setName(name);
        System.out.println("name = " + name);
        if (Objects.equals(name , "")){
            MessageDto message = new MessageDto("닉네임을 입력하세요!","/mapleLiberation", RequestMethod.GET);
            return showMessageAndRedirect(message,model);
        }
        String ocid = mapleService.getOcid(mapleRequestVo);

        mapleRequestVo.setOcid(ocid);
        mapleRequestVo.setDate(DateUtil.getDate());
        mapleRequestVo.setApiUrl("/v1/character/basic?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        BasicVo basicVo = mapleService.getBasic(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        StatVo statVo = mapleService.getStat(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/hyper-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        HyperStatVo hyperStatVo = mapleService.getHyperStat(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/item-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        ItemVo itemVo = mapleService.getItem(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/ability?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        AbilityVo abilityVo = mapleService.getAbilityVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/cashitem-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        CashItemEquipmentVo cashItemEquipmentVo = mapleService.getCashItemVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/symbol-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        SymbolVo symbolVo = mapleService.getSymbolVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/set-effect?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        SetVo setVo = mapleService.getSetVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/pet-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        PetEquipmentVo petEquipmentVo = mapleService.getPetVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/skill?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}&character_skill_grade=0");
        CharacterSkillVo skillVo = mapleService.getSkillVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/character/hexamatrix-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        HexaStatVo hexaStatVo = mapleService.getHexaVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/user/union-raider?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        UnionStatVo unionStatVo = mapleService.getUnionVo(mapleRequestVo);
        mapleRequestVo.setApiUrl("/v1/user/union-artifact?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        UnionArtifactVo unionArtifactVo = mapleService.getUnionArtifactVo(mapleRequestVo);

        InnerDto innerDto = jobCaseService.caseFilter(basicVo, name);
        String job = innerDto.getJob();
        if (Objects.equals(job, "불가능")) {
            MessageDto message = new MessageDto("4차 이전 캐릭이거나 api 등록되지 않은 캐릭터입니다!", "/mapleLiberation", RequestMethod.GET);
            return showMessageAndRedirect(message,model);
        } else if (Objects.equals(job, "데벤제논")) {
            MessageDto message = new MessageDto("데벤 제논 미구현 입니다. ㅠ","/mapleLiberation", RequestMethod.GET);
            model.addAttribute("msg", "데벤 제논 미구현 입니다. ㅠ");
            model.addAttribute("url","/getMapleInfo");
            return showMessageAndRedirect(message,model);
        }
        UnAppliedDto unAppliedDto = calculateService.calUnAppliedStat(innerDto, basicVo, hyperStatVo, symbolVo, unionStatVo, hexaStatVo);
        AppliedDto appliedDto = calculateService.calAppliedStat(innerDto, basicVo, statVo, itemVo, cashItemEquipmentVo, abilityVo, setVo, skillVo, mapleRequestVo.getDate(), unionStatVo, unionArtifactVo,petEquipmentVo);
        WeaponDto weaponDto = calWeaponService.calWeapon(innerDto, itemVo, basicVo);

        long id = saveInnerReultService.saveInnerResult(innerDto, unAppliedDto, appliedDto, weaponDto);
        InnerResultDto innerResultDto = calCombatPowerService.calCombatPower(id);
        long combatPower = innerResultDto.getCombatPower();

        String transCombat = combatUtil.transCombatType(combatPower);

        model.addAttribute("basic",basicVo);
        model.addAttribute("stat",statVo);
        model.addAttribute("hyper",hyperStatVo);
        model.addAttribute("item", itemVo);
        model.addAttribute("combat",transCombat);
        model.addAttribute("innerResult", innerResultDto);

        return "test";
    }

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params",params);
        System.out.println("params = " + params.getMessage());
        return "common/messageRedirect";
    }
}
