package com.xu.code.practice.entity;

import lombok.Data;
import org.springframework.data.convert.Jsr310Converters;

import java.time.LocalDateTime;

@Data
public class User {

    /**
     * @Author liberty
     * @Date 2025/3/13 17:32
     */
    private int id;
    private String name;
    private int height;
    private int weight;
    private String extras;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
