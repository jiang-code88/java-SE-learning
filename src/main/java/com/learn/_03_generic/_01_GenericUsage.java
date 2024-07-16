package com.learn._03_generic;

import java.util.ArrayList;

/**
 * 泛型：
 *  - 是一个标签，格式为 <数据类型>，用于在编译阶段约束只能操作某种数据类型。
 *  - 泛型和集合类都只能使用引用数据类型，不支持基本数据类型。
 * 使用泛型的好处：
 *  - 在编译阶段约束操作的数据类型，避免出现数据类型转换异常。
 */
public class _01_GenericUsage {
    public static void main(String[] args) {
        /*
        * 如果集合类不指定泛型约束，则表示可以存储任意类型的数据，使用 Object 表示集合类中数据的类型。
        *  - 集合类内部将使用 Object 类型来替代泛型标签，表示存储和操作任意类型的数据。
        *  */
        ArrayList list = new ArrayList();
        list.add(123);
        list.add("123");
        list.add(123.123);
        list.add(false);
        System.out.println(list);

        /*
        * 集合类使用泛型约束集合类只能存储 String 类型的数据。
        *  - 集合类内部将使用 String 类型来替代泛型标签，表示存储和操作 String 类型的数据。
        *  */
        ArrayList<String> listString = new ArrayList<>();
        listString.add("abc");
        listString.add("bcd");
        // listString.add(134); // 编译器将提示不可以存储非 String 类型的数据

        /*
        * 遍历 list 其中元素时，由于里面元素的类型不确定，只能使用 Object 通过多态来接受元素。
        *  - 只有 Object 中在具体类型内重写过的方法，可以调用到具体类型的实现，其他具体类型的方法均无法调用。
        *    除非使用强转，转换 Object 为具体类型后调用，但是我们无法得知 Object 多态指向的元素的真实具体类型，
        *    盲目强转具体类型会发生类型转换异常。
        *  */
        for (Object o : list) {
            // 如果 Object 多态指向的是 Integer，强转 String 会出现类型转换异常。
            // 所以我们通过泛型约定某个集合类容器就只操作一种类型的数据，规范容器的使用，避免类型混乱问题。
            int length = ((String) o).length();
        }
    }
}
