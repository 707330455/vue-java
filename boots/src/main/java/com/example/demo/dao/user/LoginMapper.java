package com.example.demo.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import com.example.demo.form.user.LoginForm;

public interface LoginMapper extends BaseMapper<User> {

    User getUser(LoginForm form);

}
