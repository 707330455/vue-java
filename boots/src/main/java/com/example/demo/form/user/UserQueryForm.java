package com.example.demo.form.user;

import lombok.Data;

@Data
public class UserQueryForm {
    private Integer current;
    private Integer size;
    private String name;
}
