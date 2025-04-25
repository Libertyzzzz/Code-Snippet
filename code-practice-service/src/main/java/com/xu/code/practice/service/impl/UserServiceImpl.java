package com.xu.code.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xu.code.practice.entity.AddUserRequest;
import com.xu.code.practice.entity.QueryRequest;
import com.xu.code.practice.entity.dto.UserDTO;
import com.xu.code.practice.mapper.UserDao;
import com.xu.code.practice.entity.User;
import com.xu.code.practice.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    private static final String TABLE_NAME = "index_optimize_display";

    /**
     * @Author liberty
     * @Date 2025/3/13 17:43
     */
    @Override
    public List<User> selectList(int offset) {

        return userDao.selectById(offset, TABLE_NAME);
    }

    @Override
    public PageInfo<User> selectPage(QueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());

        return new PageInfo<>(userDao.queryByConditions(request));
    }

    @Override
    public void saveUser(AddUserRequest request) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(request, userDto);
        userDto.setTableName(TABLE_NAME);
        userDao.insertUser(userDto);
    }


}
