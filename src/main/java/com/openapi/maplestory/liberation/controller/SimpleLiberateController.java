package com.openapi.maplestory.liberation.controller;

import com.openapi.maplestory.liberation.domain.dto.innerdto.InnerResultDto;
import com.openapi.maplestory.liberation.service.CalCombatPowerService;
import com.openapi.maplestory.liberation.service.SimpleLiberateService;
import com.openapi.maplestory.liberation.util.combatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SimpleLiberateController {
    private final CalCombatPowerService calCombatPowerService;
    private final SimpleLiberateService simpleLiberateService;


    @PostMapping("/simpleLiberate")
    public String simpleLiberate(@RequestParam("id") long id, Model model) throws ParseException {

        long SimpleLiberateId = simpleLiberateService.simpleLiberate(id);
        InnerResultDto innerResultDto = calCombatPowerService.calCombatPower(SimpleLiberateId);
        long combatPower = innerResultDto.getCombatPower();
        String transCombat = combatUtil.transCombatType(combatPower);

        model.addAttribute("id",id);
        model.addAttribute("combatPower", transCombat);

        return "simpleLiberate";
    }
}
