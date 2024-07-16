package com.learn._05_streamAPI;

import java.util.ArrayList;

/**
 * 迭代器和 Stream 流的使用对比
 *
 * Stream 流的使用流程：
 *  1. 创建 Stream 流，把数据放到「流」中。
 *  2. 在一个或多个步骤中，指定将初始 Stream 转换为另一个 Stream 的「中间操作」，
 *     「中间操作」是延迟操作，并不会立即执行。
 *  3. 使用一个「终止操作」来产生一个结果，
 *     「终止操作」是迫切的，会强制之前的延迟操作立即执行）。
 *
 * 需求：统计集合中长度大于 5 的元素。
 */
public class _01_Iterator {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("a1234");
        words.add("b12345");
        words.add("c1234");
        words.add("d12345");

        // 1 使用迭代器遍历集合中符合条件的元素个数
        int count1 = 0;
        for (String word : words) {
            if (word.length() > 5) count1++;
        }
        System.out.println("for-each-count result: " + count1);
        System.out.println("---------------------");

        // 2 使用 Stream 流遍历集合中符合条件的元素个数
        long count2 = words.stream().filter(w -> w.length() > 5).count();
        System.out.println("stream-count result: " + count2);
    }
}
