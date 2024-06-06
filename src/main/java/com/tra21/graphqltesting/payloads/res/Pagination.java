package com.tra21.graphqltesting.payloads.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int page;
    private int size;
    private Long totalPage;
    private Long totalElement;
}
