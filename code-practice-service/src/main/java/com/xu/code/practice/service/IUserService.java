package com.xu.code.practice.service;

import com.github.pagehelper.PageInfo;
import com.xu.code.practice.entity.QueryRequest;
import com.xu.code.practice.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * @Author liberty
     * @Date 2025/3/13 17:42
     */
    List<User> selectList(int offset);

    PageInfo<User> selectPage(QueryRequest request);
}
