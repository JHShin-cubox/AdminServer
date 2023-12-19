/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 장비 전원 관리 서비스
==================================================================*/


package com.adminserver.service;

import com.adminserver.dto.OnOffHistoryDTO;
import com.adminserver.mapper.OnOffHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OnOffHistoryServiceImpl implements OnOffHistoryService {

    private final OnOffHistoryMapper onOffHistoryMapper;

    List<OnOffHistoryDTO> recordList;
    @Override
    public Page<OnOffHistoryDTO> getAllHistory(Optional<Integer> page, Pageable pageable, Long deviceId, String type) {
        recordList = onOffHistoryMapper.getAllHistory(deviceId,type);
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()),recordList.size());
        return new PageImpl<>(recordList.subList(start, end), pageable, recordList.size());
    }

    @Override
    public Integer getAllCount(Long deviceId, String type) {
        return onOffHistoryMapper.getAllHistory(deviceId, type).size();
    }

}
