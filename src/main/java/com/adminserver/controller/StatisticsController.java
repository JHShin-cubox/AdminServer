package com.adminserver.controller;


import com.adminserver.dto.*;
import com.adminserver.security.TokenProvider;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.RecordManagementService;
import com.adminserver.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final RecordManagementService recordManagementService;

    @GetMapping("xray")
    public String userList(HttpServletRequest request, Model model, SearchDto searchDto){
        List<XrayStatisticDTO> lists =  statisticsService.getXrayStatisticsList(searchDto);
        List<XrayStatisticDTO> chartLists =  statisticsService.getXrayChart(searchDto);
        Integer totalCount = statisticsService.getXrayStatisticsCount(searchDto);
        model.addAttribute("sideMain","03"); // 사이드바 대메뉴
        model.addAttribute("sideOn", "on"); // 사이드바 활성화 클래스 추가
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("searchDto",searchDto);
        model.addAttribute("statisticLists", lists);
        model.addAttribute("chartLists", chartLists);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("sideTest","Y");
        return "statistics/xray";
    }

    @GetMapping("daily")
    public String dailyStatistic(HttpServletRequest request, Model model){
        model.addAttribute("sideMain","03"); // 사이드바 대메뉴
        model.addAttribute("sideOn", "on"); // 사이드바 활성화 클래스 추가
        model.addAttribute("pageum",request.getParameter("pageum"));
        List<DailyStatisticDTO> listWeek = statisticsService.getDailyStatisticWeek();
        List<DailyStatisticDTO> listMonth = statisticsService.getDailyStatisticMonth();
        List<DailyStatisticDTO> listYear = statisticsService.getDailyStatisticYear();
        model.addAttribute("listWeek", listWeek);
        model.addAttribute("listMonth", listMonth);
        model.addAttribute("listYear", listYear);
        return "statistics/dailyStatistic";
    }


    @GetMapping("test")
    public String test3D(){
        return "statistics/test";
    }
}
