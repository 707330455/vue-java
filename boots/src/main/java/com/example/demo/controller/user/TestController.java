package com.example.demo.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.user.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.form.user.UserForm;
import com.example.demo.form.user.UserQueryForm;
import com.example.demo.service.user.TestService;
import com.example.demo.util.TokenUse;
import com.example.demo.util.annotation.CheckToken;
import com.example.demo.util.getId.GenerateUniqueIdUtil;
import com.example.demo.util.handler.AuthenticationInterceptor;
import com.example.demo.util.returndata.Result;
import com.example.demo.util.returndata.ResultInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class TestController {


    @Resource
    private TestService testService;

    @Resource
    private UserMapper userMapper;

    @CheckToken
    @RequestMapping("/test")
    public ResultInfo<Object> Test(@RequestBody UserQueryForm form)
      {
          QueryWrapper<User> wrapper = new QueryWrapper<>();
          wrapper.like("name",form.getName() );//form.getName()
          IPage<User> userPage = new Page<>(form.getCurrent(), form.getSize());//参数一是当前页，参数二是每页个数
          userPage = userMapper.selectPage(userPage, wrapper);
          List<User> list = userPage.getRecords();
          return Result.Ok(userPage);
      }

//    @RequestMapping("/xml")
//    public Object TestXml() {
//        return userMapper.XmlMapper();
//    }

    @CheckToken
    @RequestMapping("/del/{id}")
    public ResultInfo<Object> TestDel(@PathVariable String id) {
        return Result.Ok(userMapper.deleteById(id));
    }

//    @RequestMapping("/del")
//    public Object TestDel(@RequestBody List<String> list) {
//        return userMapper.deleteBatchIds(list);
//    }

    @CheckToken
    @RequestMapping("/add")
    public ResultInfo<Object> add(@RequestBody UserForm form) {
        form.setVersion(1);
        GenerateUniqueIdUtil generateUniqueIdUtil = new GenerateUniqueIdUtil();
        form.setId(generateUniqueIdUtil.getGuid());
        User user = new User();
        BeanUtils.copyProperties(form, user);
        return Result.Ok("添加成功",userMapper.insert(user));
    }

    public User getuser(String id) {
        return testService.getUserByID(id);
    }

    @CheckToken
    @RequestMapping("/up")
    public ResultInfo<Object> up(@RequestBody UserForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);
        return Result.Ok("修改成功",userMapper.updateById(user));
    }
    @CheckToken
    @RequestMapping("/getName")
    public ResultInfo<Object> getName() {
        String token =  AuthenticationInterceptor.tokenGet;
        String id = TokenUse.getUserID(token);
        User user = userMapper.getUserById(id);
        if(user!=null){
            return Result.Ok("OK",user.getName());
        }else{
            return Result.Fail();
        }
    }

    @CheckToken
    @RequestMapping("/getDetail")
    public ResultInfo<Object> getDetail() {
        String token =  AuthenticationInterceptor.tokenGet;
        String id = TokenUse.getUserID(token);
        User user = userMapper.getUserById(id);
        if(user!=null){
            return Result.Ok(user);
        }else{
            return Result.Fail();
        }
    }
    @CheckToken
    @RequestMapping("/cancellationUser")
    public ResultInfo<Object> cancellationUser() {
        String token =  AuthenticationInterceptor.tokenGet;
        String id = TokenUse.getUserID(token);
        userMapper.deleteById(id);
        return Result.Ok();
    }
}
