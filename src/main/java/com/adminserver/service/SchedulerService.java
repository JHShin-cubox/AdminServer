/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 스케쥴러 서비스
==================================================================*/


package com.adminserver.service;

import com.adminserver.mapper.SchedulerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final SchedulerMapper schedulerMapper;
    @Scheduled(cron = "0 0 2 * * ?") //매일 2시에 해당 서비스 실행
    public void InsertDailyStatistic(){
        schedulerMapper.InsertDailyStatistic();
    }
}
