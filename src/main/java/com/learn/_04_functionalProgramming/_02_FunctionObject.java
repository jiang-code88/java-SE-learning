package com.learn._04_functionalProgramming;

/**
 * 普通函数：位置是固定的，要使用它，需要通过「类名.方法」找到并执行。
 * 函数对象：位置是不固定的，要在哪里使用这个函数就把这个函数对象传递进来执行即可。
 *  - 接口的目的是为了将来用它来执行函数对象，此接口中只能有一个方法定义。
 */
public class _02_FunctionObject {

    // 普通函数
    public static int add(int a, int b){
        return a + b;
    }

    // 函数接口
    public interface lambda{
        int calculate(int a, int b);
    }
    // 函数对象（将函数化为对象）
    public static lambda multiply = (a, b)->a * b;

    public static void main(String[] args) {
        int a = 2;
        int b = 3;

        // 普通函数的使用
        System.out.println(_02_FunctionObject.add(a, b));

        // 函数对象的使用
        System.out.println(multiply.calculate(a, b));
    }
}
