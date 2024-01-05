package com.openapi.maplestory.liberation.domain.controller;

import com.openapi.maplestory.liberation.domain.dto.MapleRequestVo;
import com.openapi.maplestory.liberation.domain.dto.MapleResponseVo;
import com.openapi.maplestory.liberation.domain.dto.ResponseVo;
import com.openapi.maplestory.liberation.domain.service.MapleService;
import com.openapi.maplestory.liberation.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MapleController {

    private final MapleService mapleService;

    @GetMapping("/getOcid")
    public MapleResponseVo getOcid(@RequestParam(value = "name") String name) {

        /**
         * MapleRequestVo 정의
         * apikey , baseUrl 반복해서 집어 넣어야하는데 이거 어떻케 바꿀지 생각해보기
         */
        MapleRequestVo mapleRequestVo = new MapleRequestVo();
        mapleRequestVo.setApikey("test_20d5892d17fbffcdbb4b6c8d34a5943124807b95b4f783779eea66850ce570b207887955e11ecb37b3264b7471d00004");
        mapleRequestVo.setBaseUrl("http://open.api.nexon.com/maplestory");
        mapleRequestVo.setApiUrl("/v1/id?character_name={name}");
        mapleRequestVo.setName(name);

        String ocid = mapleService.getOcid(mapleRequestVo);
        mapleRequestVo.setOcid(ocid);
        mapleRequestVo.resetApiUrl();

        ResponseVo responseVo = new ResponseVo();

        if(ocid == null){
            responseVo.setUcd("99");
            responseVo.setMessage("ocid 조회 실패");
        }else {
            responseVo.setUcd("00");
            responseVo.setMessage(ocid);
            System.out.println("ocid = " + ocid);

        }

        return getStatus(mapleRequestVo);
    }
    @GetMapping("/getStatus")
    public MapleResponseVo getStatus(MapleRequestVo mapleRequestVo){
        mapleRequestVo.setDate(DateUtil.getDate());
        mapleRequestVo.setApiUrl("/v1/character/stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");
        mapleRequestVo.setApiUrl("/v1/character/hyper-stat?ocid={mapleRequestVo.getOcid()}&date={mapleRequestVo.getDate()}");

        return mapleService.getAll(mapleRequestVo).block();
    }


}
