package com.learn._05_streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 获取 Stream 流的方式：
 *
 *  - 单列集合：通过 Collection 接口中的成员方法 stream 获取
 *  - 双列集合：获取双列集合中的单列集合，再通过单列集合 Collection 接口中的成员方法 stream 获取。
 *  - 数组：通过 Arrays 工具类中的静态方法 stream 获取。
 *  - 零散数据（要求数据类型相同）：通过 Stream 接口的静态方法 of 获取。
 *
 * 生成流：
 *  - IntStream.range(a, b)  按范围生成流（含 a，不含 b）
 *  - IntStream.rangeClosed(a, b)  按范围生成流（含 a，也含 b）
 *  - IntStream.iterate(s , p -> c)  按函数返回值生成无限数据流（s 生成初始值，p 前一个生成值，c 当前生成值）
 *  - IntStream.generate(() -> c)  按函数返回值生成无限数据流（c 当前生成值）
 */
public class _02_CreateStream {
    public static void main(String[] args) {
        // 1 单列集合获取 Stream 流
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Stream<String> listStream = list.stream();
        listStream.forEach(System.out::println);
        System.out.println("=========================");

        // 2 双列集合获取 Stream 流
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        Stream<String> mapKeyStream = map.keySet().stream();
        mapKeyStream.forEach(System.out::println);
        Stream<Map.Entry<String, Integer>> mapEntryStream = map.entrySet().stream();
        mapEntryStream.forEach(System.out::println);
        System.out.println("=========================");

        // 3 数组获取 Stream 流
        int[] arr = {1, 2, 3, 4 ,5 , 6};
        IntStream arrayStream = Arrays.stream(arr);
        arrayStream.forEach(System.out::println);
        System.out.println("=========================");

        // 4 零散数据获取 Stream 流（要求数据类型相同）
        Stream<String> dataStream = Stream.of("a", "b", "c");
        dataStream.forEach(System.out::println);
        System.out.println("=========================");

        // 5 生成流
        // 1）按范围生成流（含 a，不含 b）
        IntStream range = IntStream.range(5, 10);
        range.forEach(System.out::println);
        System.out.println("=========================");

        // 2）按范围生成流（含 a，也含 b）
        IntStream rangeClosed = IntStream.rangeClosed(5, 10);
        rangeClosed.forEach(System.out::println);
        System.out.println("=========================");
    }
}
