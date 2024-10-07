package com.learn._02_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * 注解
 *  - 作用：标记在类的成分（类、成员变量、成员方法、构造方法、方法参数）上，对成分进行编译约束、特殊标记等操作。
 *  - 注解相当于一种标记，是类的组成部分，可以给类的成分携带一些额外的信息。
 *  - 注解是给编译器或 JVM 使用的，编译器和 JVM 可以根据注解来完成对应的功能。
 *
 *  编译器约束示例：
 *   - 方法重写约束：@Override（编译器会校验带有该标记的方法，是否在其父类中存在，保证方法重写的合理和存在）
 *   - 函数式接口约束（约束接口类只能声明一个方法）：@FunctionalInterface（编译器会校验接口类，保证只存在一个方法）
 */
@AnnotationProperties(id = 100, name = "abc", information = {"a", "b"}) // 使用 {} 赋值数组类型的注解属性
@AnnotationValue("aaa")
public class AnnotationDemo {
}

/**
 * 自定义注解
 * 1 注解的属性
 *  - 在使用注解时，属性必须赋值，除非该属性设置了默认值。
 */
@interface AnnotationProperties {
    // 注解的属性
    // 格式一：数据类型 属性名();
    // 格式二：数据类型 属性名() default 默认值;
    int id();
    String name();
    String addr() default "aaa";
    String[] information();
}

/**
 * 自定义注解
 * 2 注解的特殊属性
 *  - 如果注解只有一个名为 value 的属性时，
 *    使用注解指定 value 属性值时，可以不写 value=。
 *  - 如果注解有多个属性，且除 value 属性外，有属性没有设置默认值，则 value= 不可省略。
 */
@interface AnnotationValue{
    String value();
    String name() default "zzz";
}

/**
 * 自定义注解
 * 3 元注解
 *  - 元注解是 sun 公司提供的，用来注解自定义注解。
 *
 * @Target 约束自定义注解只能在哪些地方使用
 *  - 自定义注解默认是可以在类、成员变量、成员方法、构造方法、方法参数上使用。
 *  - ElementType.TYPE        限定只能注解类、接口
 *    ElementType.METHOD      限定只能注解成员方法
 *    ElementType.FIELD       限定只能注解成员变量
 *    ElementType.PARAMETER   限定只能注解方法参数
 *    ElementType.CONSTRUCTOR 限定只能注解构造器方法
 *
 * @Retension 约束自定义注解的作用范围（有效存活范围）
 *  - 可选的作用范围：编译时、运行时。
 *  - RetentionPolicy.SOURCE  注解只作用于源码阶段，编译生成的字节码文件中不再存在
 *    RetentionPolicy.CLASS   注解只作用于源码、字节码阶段，运行期间不存在。（默认作用范围）
 *    RetentionPolicy.RUNTIME 注解作用于源码、字节码、运行期间（开发时常用，一般都配置这样的永久存活）
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 限定自定义注解只能使用在类、接口和成员方法上
@Retention(RetentionPolicy.RUNTIME)
@interface AnnotationMeta{
    int id();
    String name();
    String addr() default "aaa";
    String[] information();
}

/**
 * 自定义注解
 * 4 自定义注解的解析流程
 */
@AnnotationMeta(id = 10, name = "aaa", information = {"bbb","ccc"})
class AnnotationParse {
    public static void main(String[] args) {
        AnnotationParse annotationParse = new AnnotationParse();
        // 1 获取实例对象的字节码 Class 文件
        Class<?> annotationParseClass = annotationParse.getClass();
        // 2 判断类上是否标记 @AnnotationMeta 注解
        if (annotationParseClass.isAnnotationPresent(AnnotationMeta.class)){
            // 3 获取类上标记的 @AnnotationMeta 注解对象
            AnnotationMeta annotationMeta =
                    annotationParseClass.getDeclaredAnnotation(AnnotationMeta.class);
            // 4 打印 @AnnotationMeta 注解指定的属性值
            System.out.println(annotationMeta.id());
            System.out.println(annotationMeta.name());
            System.out.println(Arrays.toString(annotationMeta.information()));
        }
    }
}