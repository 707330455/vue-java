package com.example.demo.vos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestVo extends BaseVo {
    String s1;
    String s2;
}
