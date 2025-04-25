package com.xu.code.practice.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    /**
     * @Author liberty
     * @Date 2025/4/3 15:47
     */
    private String tableName;
    private int id;
    private String name;
    private int height;
    private int weight;
    private String extras;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
