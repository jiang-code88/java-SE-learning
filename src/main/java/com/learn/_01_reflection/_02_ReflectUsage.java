package com.learn._01_reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 反射的用途：
 *  1）可以在运行时得到一个类的全部成分然后操作。
 *  2）反射可以破坏面向对象的封装性（私有的成员变量和方法不能在外部访问），使用暴力反射破解。
 *  3）反射可以破坏泛型的约束性。
 */
public class _02_ReflectUsage {
    public static void main(String[] args) throws Exception {
        ArrayList<Double> scores = new ArrayList<>();
        scores.add(123.123);
        scores.add(234.234);
        scores.add(345.345);
        // scores.add("abc.abc"); // 泛型约束集合类中不可以放置不是 Double 类型的对象，否则编译不通过

        // 使用反射可以破坏泛型的类型约束，向集合类中放置 String 类型对象
        Method add = scores.getClass().getMethod("add", Object.class);
        Object result = add.invoke(scores, "abc.abc");
        System.out.println(scores);
    }
}
