package com.adminserver.controller;


import com.adminserver.dto.*;
import com.adminserver.service.RecordManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("record")
public class RecordController {
    private final RecordManagementService recordManagementService;
    @Value("${custom.upload-dir}")
    private String uploadDir;
    Integer nowPage;

    @GetMapping("xrayImage")
    public String xrayList(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<XrayImageDTO> imageList =  recordManagementService.getXrayImages(pageable, searchDto);
        Integer totalCount = recordManagementService.getXrayImagesCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", imageList);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/xrayImage";
    }

    @GetMapping("threeImage")
    public String threeList(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Integer totalCount = 1;
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/threeImage";
    }

    @GetMapping("threeDetail")
    public String threeDetail(){
        return "recordManagement/threeImage_detail";
    }
    @GetMapping("threeObj")
    public String threeObj(){
        return "recordManagement/threeImage_obj";
    }

    @PostMapping("xraySubImage")
    @ResponseBody
    public List<XrayImageDTO> xraySubList(@RequestParam("luggageId") String luggageId){
        return recordManagementService.getXraySubList(luggageId);
    }

    @GetMapping("luggage")
    public String LuggageLog(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<LuggageLogDTO> list =  recordManagementService.getLuggageLog(pageable, searchDto);
        Integer totalCount = recordManagementService.getLuggageLogCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/luggageLog";
    }


    @GetMapping("xrayPower")
    public String xrayPowerLog(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<DevicePowerDTO> list =  recordManagementService.getXrayPowerLog(pageable, searchDto);
        Integer totalCount = recordManagementService.getXrayPowerCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/xrayPowerLog";
    }

    @GetMapping("viewerPower")
    public String viewerPowerLog(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<DevicePowerDTO> list =  recordManagementService.getViewerPowerLog(pageable, searchDto);
        Integer totalCount = recordManagementService.getViewerPowerCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/viewerPowerLog";
    }

    @GetMapping("trsPower")
    public String trsPowerLog(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<DevicePowerDTO> list =  recordManagementService.getTrsPowerLog(pageable, searchDto);
        Integer totalCount = recordManagementService.getTrsPowerCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/trsPowerLog";
    }

    @GetMapping("login")
    public String loginLog(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<LoginHistoryDTO> list =  recordManagementService.getLoginLog(pageable, searchDto);
        Integer totalCount = recordManagementService.getLoginLogCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/loginLog";
    }
    @GetMapping("action")
    public String actionLog(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<ActionHistoryDTO> list =  recordManagementService.getActionLog(pageable, searchDto);
        Integer totalCount = recordManagementService.getActionLogCount(searchDto);
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","02"); // 사이드바 대메뉴
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        return "recordManagement/actionLog";
    }

    @PostMapping("action")
    @ResponseBody
    public String insertActionHistory(HttpServletRequest request){
            String mainMenu = request.getParameter("main");
            String subMenu = request.getParameter("sub");
            String actionType = request.getParameter("type");
            try {
                ActionHistoryDTO history = ActionHistoryDTO.builder()
                        .mainMenu(mainMenu)
                        .subMenu(subMenu)
                        .type(actionType)
                        .userId(request.getAttribute("userId").toString())
                        .build();
                recordManagementService.insertActionHistory(history);
                return "success";
            } catch (Exception e){
                e.printStackTrace();
                return "failed";
            }
    }


    @ResponseBody
    @PostMapping("/upload")
    public String  uploadImage(@RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                // 이미지 파일을 서버에 저장
                String luggageId = "QWE";
                byte[] bytes = image.getBytes();
                Path path;
                String imageName = image.getOriginalFilename();
                String duplicateCheck = recordManagementService.duplicateCheck(imageName);
                    recordManagementService.createImage(imageName, luggageId);
                    path = Paths.get(uploadDir + "/" + image.getOriginalFilename());
                Files.write(path, bytes);
                return "uploadSuccess";
            } catch (IOException e) {
                e.printStackTrace();
                return "uploadFail";
            }
        } else {
            return "uploadFail";
        }
    }
}
