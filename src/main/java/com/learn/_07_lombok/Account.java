package com.learn._07_lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
public class Account {
    @Tolerate
    private int id;
    private String name;
    private int age;
    private String gender;
    private String password;
    private String description;
    private boolean vip;
}
