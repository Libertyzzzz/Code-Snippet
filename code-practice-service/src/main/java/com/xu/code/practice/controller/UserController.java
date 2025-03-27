package com.xu.code.practice.controller;

import com.github.pagehelper.PageInfo;
import com.xu.code.practice.entity.QueryRequest;
import com.xu.code.practice.entity.User;
import com.xu.code.practice.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * @Author liberty
     * @Date 2025/3/13 17:49
     */


    @Resource
    private IUserService userService;

    @GetMapping("/all/{offset}")
    public List<User> queryById(@PathVariable int offset){
        return userService.selectList(offset);
    }

    @GetMapping("/page")
    public ResponseEntity<PageInfo<User>> queryByConditions(QueryRequest request){
        return ResponseEntity.ok(userService.selectPage(request));
    }


}
