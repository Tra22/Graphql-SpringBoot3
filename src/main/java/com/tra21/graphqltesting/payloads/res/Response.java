package com.tra21.graphqltesting.payloads.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int statusCode;
    private Pagination pagination;
    private List<T> content;
}
