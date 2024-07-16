package com.learn._04_functionalProgramming;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 「方法调用」转化为「函数对象」
 *   - 静态方法（类方法）
 *   - 非静态方法（成员方法）
 *   - 构造方法
 *
 * 「方法引用」的语法格式（理解「方法引用」的重点是找到「方法引用」对应的「函数对象」格式）
 *   1）类名::静态方法
 *      函数对象的逻辑：执行此静态方法，如 -> 类名.静态方法(...)
 *      函数对象的参数：该静态方法的参数，如 (...) -> 类名.静态方法(...)
 *   2）类名::非静态方法
 *      函数对象的逻辑：执行此非静态方法，如 -> 对象.非静态方法(...)
 *      函数对象的参数：此类对象或和加上非静态方法的参数，如 (对象, ...) -> 对象.非静态方法(...)
 *   3）对象::非静态方法
 *      函数对象的逻辑：执行此对象的非静态方法，如 -> System.out.println(...)
 *      函数对象的参数：该非静态方法的参数，如 (...) -> System.out.println(...)
 *   4）类名::new
 *      函数对象的逻辑：执行该类的构造方法，如 -> new 类名()
 *      函数对象的参数，执行构造方法的参数，如 (...) -> new 类名(...)
 *   5/6）this::非静态方法 / super::非静态方法（均属于 3）的特例）
 *        这两种方法引用只能在本类内部使用，而对象::非静态方法可以在外部类中使用。
 */
public class _06_FunctionObjectMethodReference {
    public static void main(String[] args) {
        // 1 静态方法
        // 1）lambda 表达式
        Function<String, Integer> functionA = (String s) -> Integer.parseInt(s);
        // 2）方法引用
        Function<String, Integer> functionB = Integer::parseInt;

        // 2 非静态方法
        // 1）lambda 表达式
        Function<String, Integer> functionC = (String str) -> str.length();
        // 2）方法引用
        Function<String, Integer> functionD = String::length;

        // 3 构造方法
        // 1）lambda 表达式
        Supplier<String> supplierA = () -> new String();
        // 2）方法引用
        Supplier<String> supplierB = String::new;

    }
}
