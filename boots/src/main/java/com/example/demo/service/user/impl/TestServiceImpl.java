package com.example.demo.service.user.impl;

import com.example.demo.dao.user.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.user.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private UserMapper userMapper;

    public Object Test() {
        return userMapper.selectList(null);
    }

    @Override
    public User getUserByID(String id) {
        return userMapper.getUserById(id);
    }
}
