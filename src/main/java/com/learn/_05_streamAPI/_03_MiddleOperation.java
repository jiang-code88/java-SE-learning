package com.learn._05_streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 流的中间操作
 *
 *  - filter 过滤（需要结合 Predicate 接口的函数对象使用）
 *  - distinct 去重
 *  - limit 截取前几个元素，丢弃剩余元素
 *  - skip 跳过前几个元素，保留剩余元素
 *
 *  - contact 合并流
 *
 *  - map 类型转换（映射）
 *  - flatMap 扁平化处理（降维）（需要结合 Function 接口的函数对象使用）
 *    flatMap 会将流中的集合元素打散变成集合中元素小流，然后汇入大流中。
 *
 *  - sorted 排序
 */
public class _03_MiddleOperation {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张无忌-23");
        list.add("周芷若-43");
        list.add("赵敏-45");
        list.add("张强-43");
        list.add("张三丰-43");
        list.add("张翠山-89");
        list.add("张良-23");
        list.add("王二麻子-44");
        list.add("谢广坤-54");

        /* - filter 过滤 */
        list.stream()
                .filter(s -> s.startsWith("张"))
                .forEach(System.out::println);
        System.out.println("=====================");

        /* - distinct 去重（依赖于 hashCode 和 equals 方法比较元素） */
        list.add("张无忌-23");
        list.add("张无忌-23");
        list.stream()
                .distinct()
                .forEach(System.out::println);
        System.out.println("=====================");

        /* - limit 截取前几个元素，丢弃剩余元素 */
        list.stream()
                .limit(3)
                .forEach(System.out::println);
        System.out.println("=====================");

        /* - skip 跳过前几个元素，保留剩余元素 */
        list.stream()
                .skip(3)
                .forEach(System.out::println);
        System.out.println("=====================");

        /* - concat 合并 a，b 两个流为一个流 */
        List<String> alist = Arrays.asList("a", "b", "c");
        List<String> blist = Arrays.asList("d", "e", "f");
        Stream.concat(alist.stream(), blist.stream())
                .forEach(System.out::println);
        System.out.println("=====================");

        /* - map 转换流中元素为其他类型数据（映射到其他类型数据）*/
        list.stream()
                .map(s -> Integer.parseInt(s.split("-")[1]))
                .forEach(System.out::println);
        System.out.println("=====================");

        /* - flatMap 扁平化 */
        // 示例一：
        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("D", "E", "F");
        List<List<String>> lists = Arrays.asList(list1, list2);
        // 流中的元素为一个一个的 List 集合
        lists.stream()
                .forEach(System.out::println);
        // 将元素扁平化，也就是将流中元素的集合打散转换为集合中元素的小流，汇入大流中
        lists.stream()
                .flatMap(l -> l.stream())
                .forEach(System.out::println);
        System.out.println("=====================");
        // 示例二：将二维数组转化为一维数组
        Integer [][] array2D = { // 二维数组
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        // 流中元素类型为 int[]
        Arrays.stream(array2D)
                .forEach(System.out::println);
        // 打散 int[] 类型元素为小流，汇入大流中
        // 输入二维数组
        Arrays.stream(array2D)
                .flatMap(arr -> Arrays.stream(arr))
                .forEach(System.out::println);// 输出一维数组
        System.out.println("=====================");

        /* - sorted 排序 */
        /* Comparator 比较函数的规则：
            - 返回负数，a 小 b 大；
            - 返回正数，a 大 b 小；
            - 返回 0，a 等于 b。
         */
        list.stream().sorted((a, b) -> {
            // 按年龄从小到大排序
            return Integer.compare(
                    Integer.parseInt(a.split("-")[1]),
                    Integer.parseInt(b.split("-")[1])
            );
        }).forEach(System.out::println);
        System.out.println("---------------------");
        // 从大到小的降序排序 reversed + 年龄相等时再按名字长度从小到大升序排序 thenComparing
        list.stream().sorted(
                // comparingInt 中定义了整数数值的比较规则
                Comparator.comparingInt(
                        // 定义如何从元素中取出整数数值，用于比较
                        (String s) -> Integer.parseInt(s.split("-")[1])
                ).reversed().thenComparing(
                        // 定义如何从元素中取出整数数值，用于比较
                        (String s) -> s.split("-")[0].length()
                )
        ).forEach(System.out::println);
        System.out.println("=====================");

    }
}
