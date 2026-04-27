package com.xu.code.practice.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryRequest extends BaseRequest{

    /**
     * @Author liberty
     * @Date 2025/3/18 16:08
     */

    private Integer id;
    private String name;
    private Integer height;
    private Integer weight;
    private String extras;
    LocalDateTime startTime;
    LocalDateTime endTime;

}
