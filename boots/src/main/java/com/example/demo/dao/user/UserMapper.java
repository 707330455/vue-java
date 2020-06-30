package com.example.demo.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> XmlMapper();
    User getUserById(String id);

}
