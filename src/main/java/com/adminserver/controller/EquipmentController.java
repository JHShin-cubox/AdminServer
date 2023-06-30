package com.adminserver.controller;


import com.adminserver.dto.ActionHistoryDTO;
import com.adminserver.dto.DeviceDTO;
import com.adminserver.dto.EquipmentDTO;
import com.adminserver.dto.SearchDto;
import com.adminserver.service.EquipmentService;
import com.adminserver.service.RecordManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("equip")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final RecordManagementService recordManagementService;

    @GetMapping("xray")
    public String xrayList(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<EquipmentDTO> list =  equipmentService.getXrayList(pageable,searchDto);
        Integer totalCount = equipmentService.getXrayCount(searchDto);
        Integer nowPage;
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("deviceInfoDTO", new DeviceDTO());
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "equipmentManagement/xrayList";
    }
    @GetMapping("viewer")
    public String viewerList(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<EquipmentDTO> list =  equipmentService.getViewerList(pageable, searchDto);
        Integer totalCount = equipmentService.getViewerCount(searchDto);
        Integer nowPage;
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("deviceInfoDTO", new DeviceDTO());
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "equipmentManagement/viewerList";
    }
}
