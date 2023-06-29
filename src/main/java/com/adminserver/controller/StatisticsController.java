package com.adminserver.controller;


import com.adminserver.dto.ActionHistoryDTO;
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
    public String userList(HttpServletRequest request, Model model){
        List<XrayStatisticDTO> lists =  statisticsService.getXrayStatisticsList();
        List<XrayStatisticDTO> chartLists =  statisticsService.getXrayChart();
        Integer totalCount = statisticsService.getXrayStatisticsCount();
        model.addAttribute("statisticLists", lists);
        model.addAttribute("chartLists", chartLists);
        model.addAttribute("totalCount", totalCount);
        ActionHistoryDTO history = ActionHistoryDTO.builder()
                .mainMenu("통계관리")
                .subMenu("X-Ray 자동판독 통계")
                .type("조회")
                .userId(request.getAttribute("userId").toString())
                .build();
        recordManagementService.insertActionHistory(history);
//        recordManagementService.insertActionHistory(history);
        return "statistics/xray";
    }
}
