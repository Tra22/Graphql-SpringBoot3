package com.tra21.graphqltesting.payloads.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationReq implements Serializable {
    private int page = 0;
    private int size = 10;
}
