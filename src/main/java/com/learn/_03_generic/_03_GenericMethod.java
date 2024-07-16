package com.learn._03_generic;

/**
 * 自定义泛型方法
 *  - 泛型方法格式：
 *      访问修饰符 [static] <泛型变量> 返回值类型 方法名称(形参列表){
 *
 *      }
 *  - 泛型方法的核心思想：把方法中出现泛型变量的地方全部替换为调用方法时指定的具体数据类型。
 */
public class _03_GenericMethod {

    // 该泛型方法的参数通过泛型可以接受不同类型的参数，实现对不同类型的参数进行操作
    // 需求：将任意类型数组中的所有元素，打印成字符串显示。
    public static <T> String arrayToString(T[] items){
        StringBuilder builder = new StringBuilder();
        if (items != null && items.length != 0){
            builder.append("[");
            for (int i = 0; i < items.length; i++) {
                builder.append(i == items.length - 1 ? items[i] : items[i] + "，");
            }
            builder.append("]");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        // 传递 String 类型，即指定方法操作 String 类型数据
        String[] items = {"abc", "bcd", "cde"};
        String result = arrayToString(items);
        System.out.println(result);

        // 传递 Integer 类型，即指定方法操作 Integer 类型数据
        Integer[] intItems = {123, 234,456};
        String result1 = arrayToString(intItems);
        System.out.println(result1);
    }

}
