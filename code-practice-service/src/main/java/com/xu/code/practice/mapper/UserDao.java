package com.xu.code.practice.mapper;

import com.xu.code.practice.entity.QueryRequest;
import com.xu.code.practice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * @Author liberty
     * @Date 2025/3/13 17:35
     */

    List<User> selectById(@Param("id") int id);

    List<User> queryByConditions(@Param("request") QueryRequest request);

}
