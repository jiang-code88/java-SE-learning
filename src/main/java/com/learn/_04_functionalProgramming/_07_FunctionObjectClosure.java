package com.learn._04_functionalProgramming;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * 函数式编程的「闭包」
 *  - 函数对象使用函数外部的变量数据就是闭包。
 *
 * 闭包的限制：
 *  - 闭包所使用的变量必须是 final 或 effective final，
 *    目的是为了保证函数的不变性，不会出现输入相同，但函数执行结果不同的情况。
 *  - effective final 的含义是即使变量不加 final 关键字，但是在变量第一次赋值后的代码中，
 *    不能对该变量的值进行任何修改。编译器会对此进行检查，否则编译报错。
 *  - 但是 effective final 的检查不严格，
 *    很多情况我们可以通过改变函数对象引用的外部变量值，进而控制函数对象的执行结果。
 *    如：对类/成员变量值进行修改、对引用类型变量中的成员变量值进行修改。
 *
 * 闭包的作用：
 *  - 给函数对象提供除参数以外其他的数据。
 */
public class _07_FunctionObjectClosure {
    private static int a = 6;
    private int b = 7;

    public void test(int d){
        int x = 5;

        // 1 局部变量闭包：函数对象 functionA 和外部变量 x 形成了闭包
        Function<Integer, Integer> functionA = y -> x + y;
        // x = 10; // 编译器报错，函数对象闭包引用的不是 effective final 变量。

        // 2 静态变量闭包
        Function<Integer, Integer> functionB = y -> a + y;
        // a = 10; // 可以正常运行，函数对象的执行结果会跟随修改而改变。

        // 3 成员变量闭包
        Function<Integer, Integer> functionC = y -> b + y;
        // b = 10; // 可以正常运行，函数对象的执行结果会跟随修改而改变。

        // 4 方法参数闭包
        Function<Integer, Integer> functionD = y -> d + y;
    }

    // 函数对象闭包变量的使用示例
    public static void main(String[] args) {
        // 创建十个任务对象，并且对这十个任务对象进行编号，然后运行
        ArrayList<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 由于 i 变量的值会持续改变，不符合闭包变量限制，所以此处将变量转换为 effective final
            int finalI = i + 1;
            Runnable task = () -> System.out.println(
                    // 使用闭包变量作为任务对象的编号
                    Thread.currentThread() + " : 正在运行 " + finalI
            );
            tasks.add(task);
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (Runnable task : tasks) {
            threadPool.execute(task);
        }
        threadPool.shutdown();
    }

}
