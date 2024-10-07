package com.learn._05_streamAPI;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream 的基本流（基本数据类型元素的流）
 *  - intStream
 *  - doubleStream
 *  - LongStream
 * 基本流的优点：
 *  - 和对应包装数据类型流相对，操作性能更好，空间占用更少。
 *  - 基本流有专属的操作方法。
 *
 * 普通对象流转换为基本流：
 *  - mapToInt 方法
 *  - mapToLong 方法
 *  - mapToDouble 方法
 */
public class _05_BasicStream {
    public static void main(String[] args) {
        // 1 创建 Stream 基本流
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0);
        LongStream longStream = LongStream.of(1L, 2L, 3L);

        // 2 基本流对应的原始 Stream 包装数据类型流
        Stream<Integer> integerStream = Stream.of(1, 2, 3);

        // 3 基本流的专属操作方法
        // 1）将基本流转换为包装数据类型流
        Stream<Integer> boxed = intStream.boxed();
        // 2）求和
        int sum = intStream.sum();
        // 3）求最小/最大/平均值
        OptionalInt min = intStream.min();
        OptionalInt max = intStream.max();
        OptionalDouble average = intStream.average();
        // 4）求综合统计数据（包括总个数，总和，最小，最大，平均值）
        IntSummaryStatistics intSummaryStatistics =
                intStream.summaryStatistics();

        // 4 普通对象流转换为基本流：
        ArrayList<String> list = new ArrayList<>();
        list.add("张无忌-23");
        list.add("周芷若-43");
        list.add("赵敏-45");

        IntStream listIntStream = list.stream()
                .mapToInt(
                        v -> Integer.parseInt(v.split("-")[1])
                );
    }
}
