package com.xu.code.practice.entity;

import lombok.Data;

@Data
public abstract class BaseRequest {

    /**
     * @Author liberty
     * @Date 2025/3/18 16:12
     */
    int pageNum = 0;
    int pageSize = 20;

}
