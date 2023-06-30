package com.adminserver.controller;


import com.adminserver.dto.ActionHistoryDTO;
import com.adminserver.dto.SearchDto;
import com.adminserver.dto.UserInfoDTO;
import com.adminserver.dto.XrayStatisticDTO;
import com.adminserver.security.TokenProvider;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.RecordManagementService;
import com.adminserver.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("statisticLists", lists);
        model.addAttribute("chartLists", chartLists);
        model.addAttribute("totalCount", totalCount);
        return "statistics/xray";
    }
}
