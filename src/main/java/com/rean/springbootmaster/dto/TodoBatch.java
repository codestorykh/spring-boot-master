package com.rean.springbootmaster.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoBatch {

    List<TodoBatchRequest> todoBatchRequests;

}
