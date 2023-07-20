package com.adminserver.controller;


import com.adminserver.dto.*;
import com.adminserver.service.EquipmentService;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.RecordManagementService;
import com.adminserver.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("excelDown")
public class ExcelDownController {
    private final EquipmentService equipmentService;
    private final RecordManagementService recordManagementService;
    private final StatisticsService statisticsService;
    private final OperationManagementService operationManagementService;





    @PostMapping("equipViewer")
    @ResponseBody
    public ResponseEntity<byte[]> excelEquipViewerList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<EquipmentDTO> lists =  equipmentService.excelEquipViewerList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Check PC"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("모델명");
        headerRow.createCell(1).setCellValue("위치");
        headerRow.createCell(2).setCellValue("상태값");

        for ( EquipmentDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getLocation());
            row.createCell(2).setCellValue(data.getIsOn());
        }
        try {
            workbook.write(outputStream);
            workbook.close();
            String fileName = "Check_Pc 장비.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("equipXray")
    @ResponseBody
    public ResponseEntity<byte[]> excelEquipXrayList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<EquipmentDTO> lists =  equipmentService.excelEquipXrayList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("X-Ray"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("모델명");
        headerRow.createCell(1).setCellValue("위치");
        headerRow.createCell(2).setCellValue("상태값");

        for ( EquipmentDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getLocation());
            row.createCell(2).setCellValue(data.getIsOn());
        }
        try {
            workbook.write(outputStream);
            String fileName = "X-ray 장비.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("powerXray")
    @ResponseBody
    public ResponseEntity<byte[]> excelPowerXrayList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<DevicePowerDTO> lists =  recordManagementService.excelPowerXrayList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("X-Ray"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("장비명");
        headerRow.createCell(1).setCellValue("상태값");
        headerRow.createCell(2).setCellValue("시간");

        for ( DevicePowerDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getDeviceName());
            row.createCell(1).setCellValue(data.getIsOn());
            row.createCell(2).setCellValue(data.getRegDate());
        }
        try {
            workbook.write(outputStream);
            String fileName = "X-ray 전원이력.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    @PostMapping("powerViewer")
    @ResponseBody
    public ResponseEntity<byte[]> excelPowerViewrList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<DevicePowerDTO> lists =  recordManagementService.excelPowerViewerList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Check_PC"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("장비명");
        headerRow.createCell(1).setCellValue("상태값");
        headerRow.createCell(2).setCellValue("시간");

        for ( DevicePowerDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getDeviceName());
            row.createCell(1).setCellValue(data.getIsOn());
            row.createCell(2).setCellValue(data.getRegDate());
        }
        try {
            workbook.write(outputStream);
            String fileName = "Check_PC 전원이력.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<byte[]> excelLoginList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<LoginHistoryDTO> lists =  recordManagementService.excelLoginList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("로그인이력"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("아이디");
        headerRow.createCell(1).setCellValue("IP");
        headerRow.createCell(2).setCellValue("시간");

        for ( LoginHistoryDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getUserId());
            row.createCell(1).setCellValue(data.getIp());
            row.createCell(2).setCellValue(data.getRegDate());
        }
        try {
            workbook.write(outputStream);
            String fileName = "로그인이력.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
    @PostMapping("action")
    @ResponseBody
    public ResponseEntity<byte[]> excelActionList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<ActionHistoryDTO> lists =  recordManagementService.excelActionList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("활동이력"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("아이디");
        headerRow.createCell(1).setCellValue("메인메뉴");
        headerRow.createCell(2).setCellValue("서브메뉴");
        headerRow.createCell(3).setCellValue("분류");
        headerRow.createCell(4).setCellValue("시간");

        for ( ActionHistoryDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getUserId());
            row.createCell(1).setCellValue(data.getMainMenu());
            row.createCell(2).setCellValue(data.getSubMenu());
            row.createCell(3).setCellValue(data.getType());
            row.createCell(4).setCellValue(data.getRegDate());
        }
        try {
            workbook.write(outputStream);
            String fileName = "활동이력.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("statistic")
    @ResponseBody
    public ResponseEntity<byte[]> excelStatistic(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        List<XrayStatisticDTO> lists =  statisticsService.getXrayStatisticsList(searchDto);
        String baseData = searchDto.getBaseImage().split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(baseData);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("통계"); // 엑셀 sheet 이름
        Sheet sheetImage = workbook.createSheet("통계차트"); // 차트
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("라벨명");
        headerRow.createCell(1).setCellValue("개수");

        int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();

        anchor.setCol1(3);  // 이미지를 표시할 열
        anchor.setRow1(1);  // 이미지를 표시할 행
        Picture picture = drawing.createPicture(anchor, pictureIdx);
        picture.resize();

        for ( XrayStatisticDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getLabelName());
            row.createCell(1).setCellValue(data.getAmount());
        }
        try {
            workbook.write(outputStream);
            String fileName = "X-Ray 자동판독 통계.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("userList")
    @ResponseBody
    public ResponseEntity<byte[]> excelUserList(SearchDto searchDto){
        String outputFileName;
        int rowNumber = 1;
        searchDto.setPageSize(1000000);
        searchDto.setOffset(0L);
        List<UserInfoDTO> lists =  operationManagementService.excelUserList(searchDto);
        Workbook workbook = new XSSFWorkbook();
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("사용자리스트"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("아이디");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("소속");
        headerRow.createCell(3).setCellValue("직급");
        headerRow.createCell(4).setCellValue("연락처");
        headerRow.createCell(5).setCellValue("이메일");
        headerRow.createCell(6).setCellValue("권한");

        for ( UserInfoDTO data : lists) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getUserId());
            row.createCell(1).setCellValue(data.getName());
            row.createCell(2).setCellValue(data.getDepartment());
            row.createCell(3).setCellValue(data.getRank());
            row.createCell(4).setCellValue(data.getPhone());
            row.createCell(5).setCellValue(data.getEmail());
            row.createCell(6).setCellValue(data.getRole());
        }
        try {
            workbook.write(outputStream);
            String fileName = "사용자리스트.xlsx";
            outputFileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            workbook.close();
        } catch (IOException e) { throw new RuntimeException(e); }
        headers.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.set("Content-Disposition", "attachment; fileName=\"" + outputFileName + "\"");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

}
