package com.learn._07_lombok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Tolerate;

@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor(force = true) // 生成无参构造方法（force = true 用于应对存在final修饰的成员变量）
// 相当于 @NoArgsConstructor(force = true) 的合理替代
@RequiredArgsConstructor // 无参构造函数或对需要赋值的成员变量（final修饰的成员变量）生成其对应的构造方法
@ToString()
@EqualsAndHashCode
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @ToString.Include(rank = 3)
    int id;
    @ToString.Include(rank = 1, name = "名字")
    @Builder.Default
    String name = "中明";
    @ToString.Include(rank = 2, name = "年龄")
    int age;
    String gender;

    @Setter(AccessLevel.NONE) // 不生成该成员变量的 setter 方法
    String password;

    final String description; // 常量的成员变量在类构造实例时必须要赋值，第一个赋值后将不可被赋值改变
    boolean vip;

    // 忽略成员变量 gender 与 setter 方法同名的重载方法，重新生成正确的 setter 方法
    @Tolerate
    public void setGender(int gender) {
        this.gender = String.valueOf(gender);
    }

    @ToString.Include
    public String test(){
        return "Hello world";
    }
}
