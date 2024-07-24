package com.openapi.maplestory.liberation.controller;

import com.openapi.maplestory.liberation.domain.entity.ResultInfo;
import com.openapi.maplestory.liberation.repository.dto.innerdto.*;
import com.openapi.maplestory.liberation.service.CalCombatPowerService;
import com.openapi.maplestory.liberation.service.CrudResultInfoService;
import com.openapi.maplestory.liberation.service.SimpleLiberateService;
import com.openapi.maplestory.liberation.util.combatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SimpleLiberateController {
    private final CalCombatPowerService calCombatPowerService;
    private final SimpleLiberateService simpleLiberateService;
    private final CrudResultInfoService crudResultInfoService;

    @PostMapping("/simpleLiberate")
    public String simpleLiberate(@RequestParam("id") long id, RedirectAttributes redirectAttributes) throws ParseException {

        ResultInfoDto resultInfoDto = simpleLiberateService.simpleLiberate(id);
        BasicInfoDto basicInfoDto = resultInfoDto.getBasicInfoDto();
        UnAppliedStatDto unAppliedStatDto = resultInfoDto.getUnAppliedStatDto();
        AppliedStatDto appliedStatDto = resultInfoDto.getAppliedStatDto();
        WeaponStatDto weaponStatDto = resultInfoDto.getWeaponStatDto();
        ChangeSetDto changeSetDto = resultInfoDto.getChangeSetDto();

        ResultInfoDto simpleLiberateResult = calCombatPowerService.calCombatPower(basicInfoDto, unAppliedStatDto, appliedStatDto, weaponStatDto, changeSetDto);
        resultInfoDto.setSimpleCombatPower(simpleLiberateResult.getSimpleCombatPower());

        System.out.println("resultInfoDto = " + resultInfoDto);
        long liberateId = crudResultInfoService.updateSimpleLiberate(id, resultInfoDto);
        System.out.println("liberateId = " + liberateId);

        redirectAttributes.addAttribute("id",id);
        return "redirect:/simpleLiberate/{id}";
    }

    @GetMapping("/simpleLiberate/{id}")
    public String findSimpleLiberate(@PathVariable("id") String id, Model model) {
        ResultInfo resultInfo = crudResultInfoService.readResult(Long.parseLong(id));
        String characterImage = resultInfo.getBasicInfo().getCharacter_image();
        String nickName = resultInfo.getBasicInfo().getNickName();
        long simpleCombatPower = resultInfo.getSimpleCombatPower();
        String transCombat = combatUtil.transCombatType(simpleCombatPower);

        model.addAttribute("id", id);
        model.addAttribute("img", characterImage);
        model.addAttribute("nickName", nickName);
        model.addAttribute("combatPower", transCombat);
        return "simpleLiberate";
    }
}
