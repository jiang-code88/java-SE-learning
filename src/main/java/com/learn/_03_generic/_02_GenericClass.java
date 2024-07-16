package com.learn._03_generic;

/**
 * 自定义泛型类
 *  - 泛型类格式：
 *      访问修饰符 class 类名<泛型变量>{
 *
 *      }
 *  - 泛型变量建议值：
 *    E、T、K、V
 *  - 泛型类的核心思想：把类中出现泛型变量的地方全部替换为实例化类时指定的具体数据类型。
 */
public class _02_GenericClass<E> {
    // 成员变量
    private E name;
    private E addr;

    // 成员方法
    public E add(E e){
        // ....
        return e;
    }

    public static void main(String[] args) {
        // 在实例化泛型类时，需要指定泛型的约束数据类型
        _02_GenericClass<String> stringGenericClass = new _02_GenericClass<>();
        String info = stringGenericClass.add("info");
        // stringGenericClass.add(123); // 不可以存储非 String 约束数据类型的数据

        // 如果实例化泛型类时，不指定泛型的约束数据类型，则默认使用 Object 表示任意数据类型
        _02_GenericClass objectGenericClass = new _02_GenericClass();
        Object method = objectGenericClass.add("123");
    }
}
