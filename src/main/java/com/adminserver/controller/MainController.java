/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 통합관리 시스템 메인페이지 칸트롤러
==================================================================*/

package com.adminserver.controller;


import com.adminserver.dto.OnOffHistoryDTO;
import com.adminserver.dto.XrayStatisticDTO;
import com.adminserver.dto.*;
import com.adminserver.security.JwtAuthFilter;
import com.adminserver.security.TokenProvider;
import com.adminserver.service.DeviceInfoService;
import com.adminserver.service.OnOffHistoryService;
import com.adminserver.service.XrayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final XrayService xrayService;
    private final DeviceInfoService deviceInfoService;
    private final OnOffHistoryService onOffHistoryService;
    private final TokenProvider tokenProvider;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("sideMain","");
        model.addAttribute("pageum","");
        return "index";

    }

    @GetMapping(value = "/dashBoard")
    public String DashBoard(Model model){
        model.addAttribute("sideHL","대시보드"); //사이드바 하이라이트
        model.addAttribute("viewerInfo",deviceInfoService.getViewerInfo());
        model.addAttribute("xrayInfo",deviceInfoService.getXrayInfo());
        return "dashBoard";
    }

    @GetMapping(value = "/xStat")
    public String Xray(Optional<Integer> page, Model model){
        model.addAttribute("sideHL","통계"); //사이드바 하이라이트
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<XrayStatisticDTO> xrayStatList =  xrayService.getAllStatistics(page, pageable);
        model.addAttribute("xrayStatList", xrayStatList);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",xrayService.getStatCount() );
        return "xrayStat";
    }

    @GetMapping(value = "/login")
    public String Login(){
        return "login";
    }

    @GetMapping(value = "/logOut")
    public String Logout(){
        return "logout";
    }

    @GetMapping(value ={"/record","/record/{deviceId}"})
    public String AllRecord(Optional<Integer> page, Model model, @PathVariable(value = "deviceId", required = false) Long deviceId,
                            @RequestParam(name = "type", required = false) String type){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<OnOffHistoryDTO> recordList = onOffHistoryService.getAllHistory(page, pageable, deviceId, type);
        model.addAttribute("sideHL","이력"); //사이드바 하이라이트
        model.addAttribute("recordLists", recordList);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",onOffHistoryService.getAllCount(deviceId,type));
        model.addAttribute("deviceId",deviceId);
        model.addAttribute("deviceType",type);
        model.addAttribute("selLists",deviceInfoService.getHistorySel());
        return "record";
    }

    @GetMapping("utest")
    public String UpTest(){
        return "utest";
    }
}
