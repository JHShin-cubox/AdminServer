package com.adminserver.controller;


import com.adminserver.dto.ActionHistoryDTO;
import com.adminserver.dto.ResponseDTO;
import com.adminserver.dto.UserInfoDTO;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.RecordManagementService;
import lombok.RequiredArgsConstructor;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String userList(Optional<Integer> page, Model model, HttpServletRequest request){
        int maxPage = 10;
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<UserInfoDTO> userLists =  operationManagementService.getUserList(page, pageable);
        Integer totalCount = operationManagementService.getUserCount();
        Integer nowPage;
        if(page.isEmpty()){
            nowPage  = 0;
        } else {
            nowPage = page.get();
        }
        model.addAttribute("userLists", userLists);
        model.addAttribute("nowPage", nowPage+1);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("totalCount",totalCount );
        model.addAttribute("userInfoDTO", new UserInfoDTO());
        ActionHistoryDTO history = ActionHistoryDTO.builder()
                .mainMenu("운영관리")
                .subMenu("사용자 관리")
                .type("조회")
                .userId(request.getAttribute("userId").toString())
                .build();
        recordManagementService.insertActionHistory(history);
        return "operationManagement/userList";
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
            ActionHistoryDTO history = ActionHistoryDTO.builder()
                    .mainMenu("운영관리")
                    .subMenu("사용자 관리")
                    .type("등록")
                    .userId(request.getAttribute("userId").toString())
                    .build();
        recordManagementService.insertActionHistory(history);
        return "redirect:userList?pageum=050101";
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
        ActionHistoryDTO history = ActionHistoryDTO.builder()
                .mainMenu("운영관리")
                .subMenu("사용자 관리")
                .type("수정")
                .userId(request.getAttribute("userId").toString())
                .build();

        recordManagementService.insertActionHistory(history);
        return "redirect:userList?pageum=050101";
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
    public String deleteUser(@RequestParam("userId") String userId, HttpServletRequest request){
        operationManagementService.deleteUser(userId);
        ActionHistoryDTO history = ActionHistoryDTO.builder()
                .mainMenu("운영관리")
                .subMenu("사용자 관리")
                .type("삭제")
                .userId(request.getAttribute("userId").toString())
                .build();
        recordManagementService.insertActionHistory(history);
        return "redirect:userList?pageum=050101";
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
}
