package com.adminserver.controller;


import com.adminserver.dto.ActionHistoryDTO;
import com.adminserver.dto.DeviceDTO;
import com.adminserver.dto.EquipmentDTO;
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
    public String xrayList(Optional<Integer> page, Model model, HttpServletRequest request){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<EquipmentDTO> pageList =  equipmentService.getXrayList(page, pageable);
        Integer totalCount = equipmentService.getXrayCount();
        Integer nowPage;
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("deviceLists", pageList);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("deviceInfoDTO", new DeviceDTO());
        ActionHistoryDTO history = ActionHistoryDTO.builder()
                .mainMenu("장비관리")
                .subMenu("X-Ray 장비 조회")
                .type("조회")
                .userId(request.getAttribute("userId").toString())
                .build();
        recordManagementService.insertActionHistory(history);
        return "equipmentManagement/xrayList";
    }
    @GetMapping("viewer")
    public String viewerList(Optional<Integer> page, Model model, HttpServletRequest request){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<EquipmentDTO> pageList =  equipmentService.getViewerList(page, pageable);
        Integer totalCount = equipmentService.getViewerCount();
        Integer nowPage;
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("deviceLists", pageList);
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("deviceInfoDTO", new DeviceDTO());
        ActionHistoryDTO history = ActionHistoryDTO.builder()
                .mainMenu("장비관리")
                .subMenu("Viewer 장비 조회")
                .type("조회")
                .userId(request.getAttribute("userId").toString())
                .build();
        recordManagementService.insertActionHistory(history);
        return "equipmentManagement/viewerList";
    }
    @PostMapping("modify/viewer")
    public String modifyViewer(EquipmentDTO equipmentDTO){
        EquipmentDTO equip = EquipmentDTO.builder()
                .id(equipmentDTO.getId())
                .location(equipmentDTO.getLocation())
                .name(equipmentDTO.getName())
                .type(equipmentDTO.getType())
                .build();
        equipmentService.modifyViewer(equip);
        return "redirect:/equip/viewer?pageum=010101";
    }
    @PostMapping("modify/xray")
    public String modifyXray(EquipmentDTO equipmentDTO){
        EquipmentDTO equip = EquipmentDTO.builder()
                .id(equipmentDTO.getId())
                .location(equipmentDTO.getLocation())
                .name(equipmentDTO.getName())
                .type(equipmentDTO.getType())
                .build();
        equipmentService.modifyXray(equip);
        return "redirect:/equip/xray?pageum=010201";
    }
}
