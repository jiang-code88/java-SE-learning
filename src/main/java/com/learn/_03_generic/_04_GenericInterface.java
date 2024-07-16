package com.learn._03_generic;

/**
 * 泛型接口
 *  - 泛型接口格式：
 *      访问修饰符 interface 接口名称<泛型变量>{
 *
 *      }
 *  - 泛型接口的核心思想：把接口中出现泛型变量的地方全部替换为实现接口时指定的具体数据类型。
 */
public interface _04_GenericInterface<E>{
    // 增删改查操作
    void add(E e);
    void delete();
    void update(E e);
    E query();
}

// 泛型约束只对 String 类型数据进行增删改查操作
class StringGenericInterface implements _04_GenericInterface<String> {
    @Override
    public void add(String s) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update(String s) {

    }

    @Override
    public String query() {
        return null;
    }
}

// 泛型约束只对 Integer 类型数据进行增删改查操作
class IntegerGenericInterface implements _04_GenericInterface<Integer> {
    @Override
    public void add(Integer integer) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update(Integer integer) {

    }

    @Override
    public Integer query() {
        return null;
    }
}

// 如何使用泛型接口的实现类（通过泛型接口和指定泛型变量的多态方式使用）
class GenericInterfaceUsage{
    public static void main(String[] args) {
        _04_GenericInterface<String> stringGenericInterface = new StringGenericInterface();
        stringGenericInterface.add("123");
        stringGenericInterface.delete();
        stringGenericInterface.update("234");
        String query = stringGenericInterface.query();

        _04_GenericInterface<Integer> integerGenericInterface = new IntegerGenericInterface();
        integerGenericInterface.add(123);
        integerGenericInterface.delete();
        integerGenericInterface.update(234);
        Integer query1 = integerGenericInterface.query();
    }
}
