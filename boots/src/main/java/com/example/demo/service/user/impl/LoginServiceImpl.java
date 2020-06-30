package com.example.demo.service.user.impl;

import com.example.demo.dao.user.LoginMapper;
import com.example.demo.entity.User;
import com.example.demo.form.user.LoginForm;
import com.example.demo.service.user.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;
    @Override
    public User getUser(LoginForm form) {
        User user = loginMapper.getUser(form);
        return user;
    }
}
