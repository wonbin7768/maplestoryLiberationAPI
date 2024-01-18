package com.openapi.maplestory.liberation.domain.controller;

import com.openapi.maplestory.liberation.domain.dto.BasicVo;
import com.openapi.maplestory.liberation.domain.dto.CharacterSkillVo;
import com.openapi.maplestory.liberation.domain.dto.MapleRequestVo;
import com.openapi.maplestory.liberation.domain.dto.UnionStatVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.ItemVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.SymbolVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.cash.CashItemEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.pet.PetEquipmentVo;
import com.openapi.maplestory.liberation.domain.dto.equipment.seteffect.SetVo;
import com.openapi.maplestory.liberation.domain.dto.stat.AbilityVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HexaStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.HyperStatVo;
import com.openapi.maplestory.liberation.domain.dto.stat.StatVo;
import com.openapi.maplestory.liberation.domain.service.CalculateService;
import com.openapi.maplestory.liberation.domain.service.MapleService;
import com.openapi.maplestory.liberation.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MapleController {

    private final MapleService mapleService;
    private final CalculateService calculateService;

    @GetMapping("/getMapleInfo")
    public String getMapleInfo(@RequestParam(value = "name") String name, Model model) {

        MapleRequestVo mapleRequestVo = new MapleRequestVo();
        mapleRequestVo.setApikey("test_20d5892d17fbffcdbb4b6c8d34a5943124807b95b4f783779eea66850ce570b207887955e11ecb37b3264b7471d00004");
        mapleRequestVo.setBaseUrl("http://open.api.nexon.com/maplestory");
        mapleRequestVo.setApiUrl("/v1/id?character_name={name}");
        mapleRequestVo.setName(name);

        String ocid = mapleService.getOcid(mapleRequestVo);
        model.addAttribute("ocid",ocid);
        mapleRequestVo.setOcid(ocid);

        System.out.println("ocid = " + ocid);

        mapleRequestVo.setDate(DateUtil.getDate());
        mapleRequestVo.setApiUrl("/v1/character/basic?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        BasicVo basic = mapleService.getBasic(mapleRequestVo);
        System.out.println("basic = " + basic);
        mapleRequestVo.setApiUrl("/v1/character/stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        StatVo stat = mapleService.getStat(mapleRequestVo);
        System.out.println("stat = " + stat);
        mapleRequestVo.setApiUrl("/v1/character/hyper-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        HyperStatVo hyperStatVo = mapleService.getHyperStat(mapleRequestVo);
        System.out.println("hyperStatVo = " + hyperStatVo);
        mapleRequestVo.setApiUrl("/v1/character/item-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        ItemVo itemVo = mapleService.getItem(mapleRequestVo);
        System.out.println("itemVo = " + itemVo);
        mapleRequestVo.setApiUrl("/v1/character/ability?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        AbilityVo abilityVo = mapleService.getAbilityVo(mapleRequestVo);
        System.out.println("abilityVo = " + abilityVo);
        mapleRequestVo.setApiUrl("/v1/character/cashitem-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        CashItemEquipmentVo cashItemEquipmentVo = mapleService.getCashItemVo(mapleRequestVo);
        System.out.println("cashItemEquipmentVo = " + cashItemEquipmentVo);
        mapleRequestVo.setApiUrl("/v1/character/symbol-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        SymbolVo symbolVo = mapleService.getSymbolVo(mapleRequestVo);
        System.out.println("symbolVo = " + symbolVo);
        mapleRequestVo.setApiUrl("/v1/character/set-effect?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        SetVo setVo = mapleService.getSetVo(mapleRequestVo);
        System.out.println("setVo = " + setVo);
        mapleRequestVo.setApiUrl("/v1/character/pet-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        PetEquipmentVo petEquipmentVo = mapleService.getPetVo(mapleRequestVo);
        System.out.println("petEquipmentVo = " + petEquipmentVo);
        mapleRequestVo.setApiUrl("/v1/character/skill?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}&character_skill_grade=0");
        CharacterSkillVo skillVo = mapleService.getSkillVo(mapleRequestVo);
        System.out.println("skillVo = " + skillVo);
        mapleRequestVo.setApiUrl("/v1/character/hexamatrix-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        HexaStatVo hexaStatVo = mapleService.getHexaVo(mapleRequestVo);
        System.out.println("hexaStatVo = " + hexaStatVo);
        mapleRequestVo.setApiUrl("/v1/user/union-raider?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        UnionStatVo unionStatVo = mapleService.getUnionVo(mapleRequestVo);
        System.out.println("unionStatVo = " + unionStatVo);

        List<Integer> zipUnappliedStat = calculateService.calUnAppliedStat(hyperStatVo, symbolVo, unionStatVo,hexaStatVo);
        Integer unAppliedMainStat = zipUnappliedStat.get(0);
        Integer unAppliedSubStat = zipUnappliedStat.get(1);
        calculateService.calAppliedStat(basic,stat,itemVo,cashItemEquipmentVo,petEquipmentVo,abilityVo,setVo,skillVo);


        model.addAttribute("basic",basic);
        model.addAttribute("stat",stat);
        model.addAttribute("hyper",hyperStatVo);
        model.addAttribute("item",itemVo);

        return "test";
    }

//    @GetMapping("/getStatus")
//    public Mono<Rendering> getStatus(Model model, @RequestParam(value = "ocid") String ocid) {
//        MapleRequestVo mapleRequestVo = new MapleRequestVo();
//        mapleRequestVo.setDate(DateUtil.getDate());
//        mapleRequestVo.setApiUrl("/v1/character/basic?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/hyper-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/item-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/ability?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/cashitem-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/symbol-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/set-effect?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/pet-equipment?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/character/skill?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}&character_skill_grade=0");
//        mapleRequestVo.setApiUrl("/v1/character/hexamatrix-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        mapleRequestVo.setApiUrl("/v1/user/union-raider?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
//        List<MapleResponseVo> all = mapleService.getAll(mapleRequestVo);
//        model.addAttribute("mapleResponseVo", all);
//        return Mono.just(Rendering.view("test").modelAttribute("mapleResponseVo", all).build());
//    }

//    @GetMapping("/getCalStat")
//    public Mono<ResponseVo> calStat(Mono<List<MapleResponseVo>> mapleResponseVo) {
//        return mapleResponseVo
//                .flatMap(mapleResponseList->{
//                    int level = mapleResponseList.get(0).getCharacter_level();
//                    String characterClass = mapleResponseList.get(0).getCharacter_class();
//                    List<ItemEquipmentVo> items = mapleResponseList.get(0).getItemEquipmentVo();
//                    List<SymbolEquipmentVo> symbols = mapleResponseList.get(0).getSymbolEquipment();
//                    HyperStatVo hyperStat = mapleResponseList.get(0).getHyperStat();
//                    UnionStatVo union = mapleResponseList.get(0).getUnionStatVo();
//                    List<ItemEquipmentVo> test = mapleService.calStat(level, characterClass, items, symbols, hyperStat, union);
//                    ResponseVo responseVo = new ResponseVo();
//                    responseVo.setItemEquipmentVos(test);
//
//                    return "";
//                });
//    }



}
