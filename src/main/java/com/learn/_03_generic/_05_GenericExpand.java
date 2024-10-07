package com.learn._03_generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型使用拓展
 *  - 虽然 BMW 和 BENZ 都继承了 Car
 *    但是 ArrayList<BMW>、ArrayList<BENZ> 与 ArrayList<Car> 却是没有关系的。
 *    这是因为泛型没有继承关系（太不合理了）。
 *  - 泛型通配符 ?
 *    ? 可以用在「使用泛型的时候」代表一切类型（而 E、T、K、V 是在定义泛型（定义泛型类、接口、方法）的时候用来代表一切类型的）
 *  - 泛型的上下限（限定「泛型通配符」代表一切类型的范围）
 *     ? extends Car 表示「泛型通配符」代表的一切类型限定在必须是 Car 或者 Car 的子类（泛型的上限）。
 *     ? super Car 表示「泛型通配符」代表的一切类型限定在必须是 Car 或者 Car 的父类（泛型的下限，不常用）。
 */
public class _05_GenericExpand {
    public static void main(String[] args) {
        /* 情况一 */
        // 由于泛型没有继承关系，所以约束 Car 类型数据，
        // 是无法传入 BWN 或 BENZ 类型数据的，即使 BWN 和 BENZ 与 Car 有继承关系。
        ArrayList<BMW> bmws = new ArrayList<>();
        ArrayList<BENZ> benzs = new ArrayList<>();
        // run(bmws);   // 编译报错，无法支持传入
        // run(benzs);  // 编译报错，无法支持传入

        /* 情况二 */
        // 使用泛系通配符「?」可以使得在使用泛型的时候，接受 Car、BMW、BENZ 类型的数据。
        run_2(bmws);
        run_2(benzs);
        // 但是存在一个问题，如果是一个 Dog 类型的数据，也可以传递给 cars 参数，
        // 明显 dog 和 car 是不匹配的，泛型通配符明显不合适，违背设计原则了。
        ArrayList<Dog> dogs = new ArrayList<>();
        run_2(dogs);

        /* 情况三 */
        // 使用泛型的上下限，使得 cars 只能传入 Car 或 Car 子类类型的数据
        run_3(bmws);
        run_3(benzs);
        // run_3(dogs); // 编译报错，无法支持传入 Dog 类型数据
    }

    // run 方法参数这里是使用泛型，约束只能传入存储 Car 类型数据的 ArrayList 集合类
    public static void run(ArrayList<Car> cars){ }

    public static void run_2(ArrayList<?> cars){ }

    public static void run_3(ArrayList<? extends Car> cars){ }
}

class Car{ }

class BMW extends Car{ }

class BENZ extends Car{ }

class Dog{ }
