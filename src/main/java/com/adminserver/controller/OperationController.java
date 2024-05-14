/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 통합관리 시스템 운영관리 칸트롤러
==================================================================*/

package com.adminserver.controller;

import com.adminserver.dto.*;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.RecordManagementService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("operation")
public class OperationController {
    private final OperationManagementService operationManagementService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RecordManagementService recordManagementService;

    @GetMapping("userList")
    public String userList(Optional<Integer> page, Model model, HttpServletRequest request, SearchDto searchDto){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<UserInfoDTO> list =  operationManagementService.getUserList(pageable, searchDto);
        Integer totalCount = operationManagementService.getUserCount(searchDto);
        Integer nowPage;
        if(page.isEmpty()) nowPage  = 0;
        else nowPage = page.get();
        model.addAttribute("sideMain","05"); // 사이드바 대메뉴
        model.addAttribute("pageum",request.getParameter("pageum"));
        model.addAttribute("uri",request.getRequestURI());
        model.addAttribute("lists", list);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("searchDto",searchDto);
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("userInfoDTO", new UserInfoDTO());
        return "operationManagement/userList";
    }

    @GetMapping("updateClass")
    public String updateClassInfo(){
        return "operationManagement/updateClass";
    }

    @PostMapping("userSignUp")
    public String userSignUp(@Validated UserInfoDTO userInfoDTO, BindingResult bindingResult, Model model, HttpServletRequest request){
        UserInfoDTO user = UserInfoDTO.builder()
                .userId(userInfoDTO.getUserId())
                .password(passwordEncoder.encode(userInfoDTO.getPassword()))
                .name(userInfoDTO.getName())
                .rank(userInfoDTO.getRank())
                .department(userInfoDTO.getDepartment())
                .phone(userInfoDTO.getPhone())
                .email(userInfoDTO.getEmail())
                .role(userInfoDTO.getRole())
                .build();
            operationManagementService.createUser(user);
        String redirectUrl = userInfoDTO.getNUrl();
        return "redirect:" + "http://localhost:8080/operation/userList?pageum=050101";
    }
    @PostMapping("userModify")
    public String userModify(@Validated UserInfoDTO userInfoDTO, BindingResult bindingResult, Model model, HttpServletRequest request){
        UserInfoDTO user = UserInfoDTO.builder()
                .userId(userInfoDTO.getUserId())
                .password(passwordEncoder.encode(userInfoDTO.getPassword()))
                .name(userInfoDTO.getName())
                .rank(userInfoDTO.getRank())
                .department(userInfoDTO.getDepartment())
                .phone(userInfoDTO.getPhone())
                .email(userInfoDTO.getEmail())
                .role("USER")
                .build();
        operationManagementService.updateUser(user);
        String redirectUrl = userInfoDTO.getNUrl();
        return "redirect:" + redirectUrl;
    }

    @PostMapping("duplicateCheck")
    @ResponseBody
    public String duplicateCheck(@RequestParam String userId){
        String existId = operationManagementService.duplicateCheck(userId);
        if(existId != null){
            return "N";
        } else { return "Y";}
    }

    @PostMapping("userDelete")
    public String deleteUser(@RequestParam("userId") String userId, @RequestParam("dUrl") String dUrl, HttpServletRequest request){
        operationManagementService.deleteUser(userId);

        return "redirect:" + dUrl;
    }
    @GetMapping("signout")
    public String signOutUser() {
        // maxAge를 0으로 만들어 쿠키에 저장된 토큰 삭제
       ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("accessToken", "")
                        .path("/")
                        .maxAge(0)
                        .build().toString())
                .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("refreshToken", "")
                        .path("/")
                        .maxAge(0)
                        .build().toString())
                .body(ResponseDTO.builder()
                        .status(HttpStatus.OK.value())
                        .data(List.of())
                        .build());
        return "redirect:/";
    }

    @GetMapping("setting")
    public String monitoringSetting(Model model, HttpServletRequest request){
        model.addAttribute("setting",operationManagementService.getSetting());
        model.addAttribute("sideMain","05"); // 사이드바 대메뉴
        model.addAttribute("pageum",request.getParameter("pageum"));
        return "operationManagement/monitoringSetting";
    }

    @PostMapping("labelUpload")
    @ResponseBody
    public void excelLabelClassInsert(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            int rowNumber = 0;
            operationManagementService.classFlagChange();
            for (Row row : sheet) {
                rowNumber++;

                // 첫 번째 행은 헤더이므로 무시하고 다음 행으로 넘어감
                if (rowNumber == 1) continue;
                Cell cell0 = row.getCell(0);
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                LabelDTO labelDTO = new LabelDTO();

                if (cell0 != null) {
                    labelDTO.setLabelId(cell0.getStringCellValue());
                } else{
                    labelDTO.setLabelId("");
                }

                if (cell1 != null || !cell1.equals("")) {
                    labelDTO.setLabelEng(cell1.getStringCellValue());
                } else{
                    labelDTO.setLabelEng(null);
                }

                if (cell2 != null) {
                    labelDTO.setLabelKor(cell2.getStringCellValue());
                } else{
                    labelDTO.setLabelKor("");
                }

                operationManagementService.insertLabelClass(labelDTO);

            }
        }
    }
}
