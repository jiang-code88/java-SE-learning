package com.learn._04_functionalProgramming;

import java.math.BigInteger;

/**
 * 函数对象的类型
 *  1）函数参数个数和参数类型相等
 *  2）函数返回值类型相等
 *  的函数对象归为同一种类型，使用函数式接口定义。
 *
 *  - 函数式接口：仅包含一个方法定义的接口类，
 *      使用 @FunctionalInterface 注解标记，编译器会检查接口只能存在一个方法定义。
 *
 *  - 函数式编程核心思想：不需要关心函数的具体规则，只需关注函数的入参和出参即可。
 *
 *  - JDK 提供的内置函数式接口（已默认提供，无需我们自定义，可以直接使用）
 *    - Runnable 应用在多线程编程中，表示任务对象
 *      () -> void
 *    - Callable 应用在多线程编程中，表示有返回值的任务对象
 *      () -> T
 *    - Comparator
 *      (T, T) -> int
 *    - Consumer, IntConsumer, LongConsumer, DoubleConsumer，BiConsumer
 *      (T) -> void 有参数+无返回值的函数对象，Bi 代表两个参数，Int 代表参数类型为 int，以此类推
 *    - Function, IntFunction, LongFunction, DoubleFunction, BiFunction
 *      (T) -> R 有参数+有返回值的函数对象，Bi 代表两个参数，Int 代表参数类型为 int，以此类推
 *    - Predicate, IntPredicate, LongPredicate, DoublePredicate, BiPredicate
 *      (T) -> boolean 有参数+boolean类型返回值的函数对象，Bi 代表两个参数，Int 代表参数类型为 int，以此类推
 *    - Supplier, IntSupplier, LongSupplier, DoubleSupplier
 *      () -> T 无参数+有返回值的函数对象，Int 代表返回值类型为 int，以此类推
 *    - UnaryOperator, IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator, BinaryOperator
 *      (T) -> T 参数和返回值类型相等的函数对象，Bi 代表两个类型相等的参数，Int 代表参数和返回值类型均为 int，以此类推
 *   参数个数前缀：
 *    - Unary 一元
 *    - Binary 二元
 *    - Ternary 三元
 *    - Quaternary 四元
 */
public class _05_FunctionObjectType {
    public static void main(String[] args) {
        // 函数对象类型为 TypeA 的函数对象变量
        TypeA typeA = (int a) -> a == 0;
        TypeA typeA1 = (int a) -> BigInteger
                .valueOf(a).isProbablePrime(100);

        // 函数对象类型为 TypeB 的函数对象变量
        TypeB typeB = (int a, int b) -> a + b;
        TypeB typeB1 = (int a, int b) -> a * b;

        // 使用泛型拓展函数式接口，让函数式接口能够更为通用
        TypeC<Integer> integerTypeC = (String a) -> Integer.valueOf(a);
        TypeC<Long> longTypeC = (String a) -> Long.valueOf(a);
        TypeC<String> stringTypeC = (String a) -> a;
        // 泛型函数式接口：<返回值类型，参数类型>
        TypeD<String, Integer> integerStringTypeD = a -> Integer.valueOf(a);
        TypeD<String, Long> stringIntegerTypeD = a -> Long.valueOf(a);
    }

    @FunctionalInterface
    interface TypeA{
        boolean op(int a);
    }

    @FunctionalInterface
    interface TypeB{
        int op(int a, int b);
    }

    @FunctionalInterface
    interface TypeC<T>{
        T op(String a);
    }

    @FunctionalInterface
    interface TypeD<T, R>{
        R op(T a);
    }

}
