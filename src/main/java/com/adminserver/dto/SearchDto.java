package com.adminserver.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SearchDto {
    private String searchDateType;
    private String searchBy;
    private String searchQuery = "";
    private String searchCheck;
    private String searchCategory;
    private String searchText = "";
    private String dateStart = "";
    private String dateEnd = "";
    private String pageum;
    private Long offset;
    private Integer pageSize;
}
