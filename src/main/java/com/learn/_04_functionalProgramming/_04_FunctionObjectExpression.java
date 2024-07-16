package com.learn._04_functionalProgramming;

/**
 * 函数对象的表现形式
 *  - lambda 表达式：功能更加全面。
 *  - 方法引用：写法更加简洁。
 */
public class _04_FunctionObjectExpression {
    /* lambda 表达式（语法格式：方法参数 + 箭头符号 + 方法逻辑） */
    // 示例一：明确指定参数类型
    // (int a, int b) -> a + b ;

    // 示例二：代码多于一行时，不能省略 {} 和 return
    // (int a, int b) -> { int c = a + b; return c }

    // 示例三：可以根据上下文推导出 lambda 表达式的参数类型时，可以省略不写参数类型
    // Lambda1 lambda = (a, b) -> a + b;
    // interface Lambda1{
    //      int op(int a, int b);
    // }

    // 示例四：只有一个参数时，可以省略()
    // a -> a;


    /* 方法引用：其实也是一个函数对象，
       函数对象的内部逻辑就是调用该方法引用所指定的方法，
       调用该方法时我们所未知的部分就是方法调用所需的参数。
     */
    // 示例一：类型::静态方法
    // Math::max // 代表传入两个参数，一个返回结果的函数对象
    // (int a, int b) -> Math.max(a, b)； // 与上述函数对象对应的 Lambda 表达式

    // 示例二：类型::成员方法
    // Student::getName
    // (Student stu) -> stu.getName();   // 与上述函数对象对应的 Lambda 表达式

    // 示例三：对象::成员方法
    // System.out::println
    // (Object obj) -> System.out.println(obj); // 与上述函数对象对应的 Lambda 表达式

    // 示例四：类型::new 关键字
    // Student::new
    // () -> new Student(); // 与上述函数对象对应的 Lambda 表达式
}
