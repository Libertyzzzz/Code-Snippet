package com.xu.code.practice.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddUserRequest {

    /**
     * @Author liberty
     * @Date 2025/4/3 15:55     */

    private String name;
    private int height;
    private int weight;
    private String extras;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
