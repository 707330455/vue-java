package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class User {
    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    private String name;
    private int age;
    private String address;
    private int version;
    private String description;
    private String telephone;
    private String sex;
    private String pwd;
    @TableLogic
    private boolean deleted;
}
