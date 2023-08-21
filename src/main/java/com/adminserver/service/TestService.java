package com.adminserver.service;


import com.adminserver.mapper.RecordManagementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final RecordManagementMapper recordManagementMapper;

    public Integer createImage(String imageName, String luggageId){
        return recordManagementMapper.createImage(imageName,luggageId);
    }
}
