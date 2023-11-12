package com.rean.springbootmaster.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoBatchResponse {

    private Long id;

    private String name;

    private String description;

    private String status;

    private Integer totalPage;

    private long totalRecord;

    private Integer currentPage;

    private Integer pageSize;
}
