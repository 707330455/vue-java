package com.example.demo.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.user.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.form.user.LoginForm;
import com.example.demo.form.user.RegisterForm;
import com.example.demo.form.user.resetPasswordForm;
import com.example.demo.service.user.LoginService;
import com.example.demo.util.TokenUse;
import com.example.demo.util.annotation.LoginToken;
import com.example.demo.util.getId.GenerateUniqueIdUtil;
import com.example.demo.util.returndata.Result;
import com.example.demo.util.returndata.ResultInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private UserMapper userMapper;

    @LoginToken
    @RequestMapping("/login")
    public ResultInfo<Object> login(@RequestBody LoginForm form)
    {
//验证用户登陆信息于数据库中的用户信息是否一致
//        UserMainBean ub = userMainServerl.userLogin(userCode, password);
        User ub = loginService.getUser(form);
        if (ub != null) {
            if(ub.isDeleted()){
                return Result.Fail("用户不存在");
            }
            String token= TokenUse.sign(ub.getName(),ub.getId());
            if (token!=null){
                return Result.Ok("登陆成功",token);
            }
        }
        return Result.Fail("用户不存在");
    }
    @LoginToken
    @RequestMapping("/register")
    public ResultInfo<Object> register(@RequestBody RegisterForm form)
    {
        GenerateUniqueIdUtil generateUniqueIdUtil = new GenerateUniqueIdUtil();
        form.setId(generateUniqueIdUtil.getGuid());
        User user = new User();
        BeanUtils.copyProperties(form, user);
        userMapper.insert(user);

        if (user != null) {
            String token= TokenUse.sign(user.getName(),user.getId());
            if (token!=null){
                return Result.Ok("注册成功",token);
            }
        }
        return Result.Fail("注册失败");
    }
    @LoginToken
    @RequestMapping("/resetPassword")
    public ResultInfo<Object> resetPassword(@RequestBody resetPasswordForm form)
    {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getName, form.getName())
                .eq(User::getTelephone, form.getTelephone());
        User user = userMapper.selectOne(queryWrapper);
        if(user!=null){
            user.setPwd("123456");
            userMapper.updateById(user);
            return Result.Ok();
        }else {
            return Result.Fail();
        }
    }
}
