package com.example.demo.service.user;

import com.example.demo.entity.User;
import com.example.demo.form.user.LoginForm;

public interface LoginService {
    User getUser(LoginForm form);
}
