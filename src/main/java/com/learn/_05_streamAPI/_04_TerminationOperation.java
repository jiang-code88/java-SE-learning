package com.learn._05_streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Stream 流的终结操作
 *
 *  - foreach 遍历元素
 *  - findFirst 在流中只挑出第一个元素返回
 *  - findAny 在流中只挑出任意一个元素返回
 *
 *  - anyMatch 如果流中有任意一个元素满足条件，则返回真
 *  - allMatch 如果流中所有元素满足条件，则返回真
 *  - noneMatch 如果流中没有元素满足条件，则返回真
 *
 *  - reduce 化简（两两合并，只剩一个；适合求最大值，最小值，求和等场景）
 *  - count 统计元素个数
 *  - max 统计元素的最大值
 *  - min 统计元素的最小值
 *  - sum 对流中元素求和
 *  - average 对流中元素求平均值
 *
 *  - collect 将流中元素收集入容器
 *    - 使用 JDK 提供的默认收集器收集元素
 *      - toList
 *      - toSet
 *      - toMap(x->k, x->v) 从 x 中提取出 map 的 k，v
 *    - groupBy(x->k,dc) 收集器对流中元素进行分组后再收集（需要搭配下游收集器使用）
 *      从 x 中提取出 k 分组，用下游收集器 dc 收集
 *      - 分组后对组内元素进行类型转换再收集 mapping(x->y,dc) 将 x 转换为 y，用下游收集器 dc 收集
 *      - 分组后对组内元素进行过滤后再收集 filtering(x->boolean,dc) x 判断为 true 时，用下游收集器 dc 收集
 *      - 分组后统计每组元素的数量 counting
 *      - 分组后统计每组元素的最大值 maxBy((a,b)->int)
 *      - 分组后统计每组元素的最小值 minBy((a,b)->int)
 *      - 分组后统计每组元素的总和 summingInt(x->int) 转换为 int 后求和
 *      - 分组后统计每组元素的平均值 averagingDouble(x->int) 转换为 int 后求平均值
 *      - 分组后对每组元素进行两两合并化简 reducing（使用 reducing 模拟 summingInt 操作）
 *        reduce(init,(p,x)->r) init 初始值，用上次结果 p 和当前元素 x 生成本次结果 r
 *    - partitioningBy(x->boolean,dc) 按条件分区，用下游收集器 dc 收集
 *
 *  - toArray 收集流中数据到数组
 */
public class _04_TerminationOperation {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "张无忌-23", "周芷若-43", "赵敏-45", "张强-43", "张三丰-43",
                "张翠山-89", "张良-23", "王二麻子-44", "谢广坤-54"
        );

        /* - foreach 遍历元素 */
        list.stream().forEach(System.out::println);
        System.out.println("=========================");

        /* - findFirst 在流中只挑出第一个元素返回 */
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        // 查找第一个偶数
        Optional<Integer> first = integerList.stream()
                .filter(v -> v % 2 == 0)
                .findFirst();
        first.ifPresent(System.out::println);
        System.out.println("=========================");

        /* - findAny 在流中只挑出任意一个元素返回 */
        //   - 在如下的串行流场景下，findAny 和 findFirst 行为是相同的
        //     在并行流的场景场景下，才能实现返回任意一个偶数。
        // 查找任意一个偶数
        Optional<Integer> any = integerList.stream()
                .filter(v -> v % 2 == 0)
                .findAny();
        any.ifPresent(System.out::println);
        System.out.println("=========================");

        /* - anyMatch 如果流中有任意一个元素满足条件，则返回真 */
        boolean anyMatch = integerList.stream().anyMatch(v -> v == 4);
        System.out.println(anyMatch);
        System.out.println("=========================");
        /* - allMatch 如果流中所有元素满足条件，则返回真 */
        boolean allMatch = integerList.stream().allMatch(v -> v < 10);
        System.out.println(allMatch);
        System.out.println("=========================");
        /* - noneMatch 如果流中没有元素满足条件，则返回真 */
        boolean noneMatch = integerList.stream().noneMatch(v -> v == 10);
        System.out.println(noneMatch);
        System.out.println("=========================");

        /* - reduce 化简 */
        // 用法一：.reduce(p, x) -> r); p 上次合并结果，x 当前元素，r 本次合并结果
        // 用法二：.reduce(init, (p, x) -> r); init 初始值，p 上次合并结果，x 当前元素，r 本次合并结果
        // 需求：获取年龄的最大值
        Optional<String> reduced = list.stream().reduce((curMax, current) -> {
            // 写法一
            /*
            // 定义年龄大小比较器
            Comparator<String> ageComparator = Comparator.comparingInt(
                    // 定义从流中元素获取年龄的逻辑
                    s -> Integer.parseInt(s.split("-")[1])
            );
            // 比较获取年龄最大值的元素
            if(ageComparator.compare(curMax, current) < 0){
                return current;
            }
            return curMax;
            */

            // 写法二（推荐，理由是这样更直观，简洁）
            return Integer.parseInt(curMax.split("-")[1]) >
                    Integer.parseInt(current.split("-")[1]) ? curMax : current;
        });
        reduced.ifPresent(System.out::println);
        System.out.println("----------------------");
        // 可以设置初始值，该初始值会被放入流中作为元素执行 reduce 化简操作，
        // 当流中没有任何元素时，该初始值元素将充当最终结果输出。
        ArrayList<String> arrayList = new ArrayList<>();
        String reducedResult = arrayList.stream().reduce(
                "can not be reduced-55",
                (s1, s2) -> Integer.parseInt(s1.split("-")[1]) >
                        Integer.parseInt(s2.split("-")[1]) ? s1 : s2
        );
        System.out.println(reducedResult);
        System.out.println("----------------------");
        // 需求：计算元素的个数
        // 先将每个元素类型转换为数字 1，然后设置初始值 0，把每个元素的 1 累加起来，就是元素的个数了
        Integer reducedResult_2 = list.stream()
                .map(v -> 1)
                .reduce(0, (sum, v) -> sum + v);
        System.out.println(reducedResult_2);
        System.out.println("=========================");

        /* - count 统计元素个数 */
        long count = list.stream().count();
        System.out.println(count);
        System.out.println("=========================");

        /* - max 统计元素的最大值 */
        Optional<String> max = list.stream()
                .max(
                        Comparator.comparingInt(
                                s -> Integer.parseInt(s.split("-")[1])
                        )
                );
        max.ifPresent(System.out::println);
        System.out.println("=========================");

        /* - min 统计元素的最小值 */
        Optional<String> min = list.stream()
                .min(
                        Comparator.comparingInt(
                                s -> Integer.parseInt(s.split("-")[1])
                        )
                );
        min.ifPresent(System.out::println);
        System.out.println("=========================");

        /* - sum 对流中元素求和 */
        // 流中元素需要由对象类型转换为基本数值类型后才能进行运算
        int sum = list.stream()
                .mapToInt(s -> Integer.parseInt(s.split("-")[1]))
                .sum();
        System.out.println(sum);
        OptionalDouble average = list.stream()
                .mapToInt(s -> Integer.parseInt(s.split("-")[1]))
                .average();
        average.ifPresent(System.out::println);

        /* - toArray 收集流中数据到数组 */
        //   - 参数 value 的值表示流中数据的个数，所以我们根据流中数据的个数创建一个数组。
        //   - toArray 方法底层会依次将流中的每一个元素放入实例化的数组中，
        //     然后 toArray 方法的返回值就是一个装着流中所有数据的数组。
        String[] listArray = list.stream().toArray(len -> new String[len]);
        System.out.println(Arrays.toString(listArray));
        /* 额外知识：集合类也可以通过直接调用 toArray 方法转变为元素数组。
        toArray() 方法可以指定参数，参数是指定了具体类型的数组对象，
        集合中所有的数据将被放到该具体数组对象中。
        需要注意的是，如果集合的长度 > 数组长度，会根据实际数据的个数，重新创建该类型的数组，
        如果集合的长度 <= 数组长度，不会重新创建新数组，元素直接放入该数组。 */
        String[] array = list.toArray(new String[0]);
        System.out.println("=========================");

        /* - collect 将流中元素收集入容器 */
        // 用法：.collect(()->c, (c, x)->void, ()->void)
        //       - ()->c        定义如何创建容器 c
        //       - (c, x)->void 定义如何将元素 x 加入容器 c
        //       - ()->void
        // 需求：收集流中元素到 list 集合
        ArrayList<String> collectList = list.stream()
                .collect(
                        () -> new ArrayList<>(), // ArrayList::new
                        (l, x) -> l.add(x),      // ArrayList::add
                        (a, b) -> { }
                );
        System.out.println(collectList);
        System.out.println("----------------------");
        // 需求：收集流中元素到 set 集合
        HashSet<String> collectSet = list.stream()
                .collect(
                        () -> new HashSet<>(),
                        (set, x) -> set.add(x),
                        (a, b) -> { }
                );
        System.out.println(collectSet);
        System.out.println("----------------------");
        // 需求：收集流中元素到 map 容器
        HashMap<String, String> collectMap = list.stream()
                .collect(
                        () -> new HashMap<>(),
                        (map, x) -> map.put(x.split("-")[0], x.split("-")[1]),
                        (a, b) -> { }
                );
        System.out.println(collectMap);
        System.out.println("----------------------");
        // 需求：收集流中元素拼接成字符串
        StringBuilder collectStringBuilder = list.stream()
                .collect(
                        () -> new StringBuilder(),
                        (s, x) -> s.append(x),
                        (a, b) -> { }
                );
        System.out.println(collectStringBuilder);
        System.out.println("----------------------");
        // 需求：收集流中元素拼接成字符串（逗号分隔）
        StringJoiner collectStringJoiner = list.stream()
                .collect(
                        () -> new StringJoiner("/"),
                        (s, x) -> s.add(x),
                        (a, b) -> { }
                );
        System.out.println(collectStringJoiner);
        System.out.println("----------------------");

        /* JDK 提供的默认收集器（Collectors 工具类中定义的一系列静态方法） */
        // 一个内置的收集器（Collector 接口的实现类）中包含了我们上面收集时定义的三种函数对象，可以简化我们的代码编写。
        List<String> toList = list.stream().collect(Collectors.toList());
        Set<String> toSet = list.stream().collect(Collectors.toSet());
        Map<String, String> toMap = list.stream()
                .collect(Collectors.toMap(
                        x -> x.split("-")[0], // 定义如何从当前元素获取 key 的函数对象
                        x -> x.split("-")[0]) // 定义从当前元素获取 value 的函数
                );
        String toStringBuilder = list.stream().collect(Collectors.joining());
        String toStringJoiner = list.stream().collect(Collectors.joining(","));

        /* - groupBy 对流中元素进行分组收集的收集器（需要搭配下游收集器使用） */
        // 需求：先按元素的字符串长度进行分组，然后将每组元素收集到 List 集合中
        Map<Integer, List<String>> collectGroupBy = list.stream()
                .collect(Collectors.groupingBy( //「上游收集器」
                    x -> x.length(),     // 定义「上游收集器」分类元素的分组键（按元素的字符串长度分组）
                    Collectors.toList()  // 「下游收集器」 定义分组后每组元素如何收集（每组元素收集到 List 中）
                )); // 结果输出是 Map，key 是分组键，value 是每组元素的列表
        System.out.println(collectGroupBy);
        System.out.println("----------------------");
        // 需求：先按元素的字符串长度进行分组，然后对每组元素进行转换再收集到 List 集合中
        Map<Integer, List<String>> collectGroupByMapping = list.stream()
                .collect(Collectors.groupingBy(
                    x -> x.length(), // 定义上游收集器分类元素的分组键
                    // 定义下游收集器，先转换元素类型再收集每组元素到 List 中
                    Collectors.mapping(x -> x.split("-")[0], Collectors.toList())
                ));
        System.out.println(collectGroupByMapping);
        System.out.println("----------------------");
        // 需求：先按元素的字符串长度进行分组，然后统计每组元素的数量
        Map<Integer, Long> collectGroupByCounting = list.stream()
                .collect(Collectors.groupingBy(
                        x -> x.length(),
                        Collectors.counting()
                ));
        System.out.println(collectGroupByCounting);
        System.out.println("----------------------");
        // 需求：先按元素的字符串长度进行分组，然后统计每组中年龄最小（最大）的元素
        Map<Integer, Optional<String>> collectGroupByMinBy = list.stream()
                .collect(Collectors.groupingBy(
                        x -> x.length(),
                        Collectors.minBy(
                                Comparator.comparingInt(
                                        s -> Integer.parseInt(s.split("-")[1])
                                )
                        )
                ));
        System.out.println(collectGroupByMinBy);
        System.out.println("----------------------");
        // 需求：按元素的字符串长度进行分组，统计每组中年龄的平均值
        Map<Integer, Double> collectGroupByAveragingDouble = list.stream()
                .collect(Collectors.groupingBy(
                        x -> x.length(),
                        Collectors.averagingDouble(s -> Integer.parseInt(s.split("-")[1]))
                ));
        System.out.println(collectGroupByAveragingDouble);
        System.out.println("----------------------");
        // 需求：使用 reducing 实现 summingInt 的效果，统计每组中年龄的总和
        // 其实分组后的所有统计操作都是基于 reducing 实现的
        Map<Integer, Integer> collectGroupByReducing = list.stream()
                .collect(Collectors.groupingBy(
                        x -> x.length(),
                        Collectors.mapping(
                                s -> Integer.parseInt(s.split("-")[1]),
                                Collectors.reducing(0, (p, x) -> p + x)
                        )
                ));
        System.out.println(collectGroupByReducing);
    }
}
