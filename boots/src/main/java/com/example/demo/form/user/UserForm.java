package com.example.demo.form.user;


import lombok.Data;
import lombok.Setter;
@Setter
@Data
public class UserForm {
    private String id;
    private String name;
    private int age;
    private String telephone;
    private String sex;
    private String description;
    private String address;
    private int version;
    private String pwd;
}
