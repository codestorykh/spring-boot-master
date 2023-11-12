package com.rean.springbootmaster.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoBatchRequest {

    private Long id;

    private String name;

    private String description;

    private String status;

}
