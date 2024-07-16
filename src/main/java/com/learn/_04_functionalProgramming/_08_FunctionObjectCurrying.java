package com.learn._04_functionalProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 函数式编程的函数对象柯里化
 *  - 什么是柯里化：让接受多个参数的函数转换成一系列接受一个参数的函数。
 *  - 如何实现柯里化：结合闭包实现
 *  - 柯里化的作用：让函数分步执行
 */
public class _08_FunctionObjectCurrying {

    @FunctionalInterface
    interface F{
        List<Integer> op(List<Integer> liatA, List<Integer> listB, List<Integer> listC);
    }

    @FunctionalInterface
    interface Fa{
        Fb op(List<Integer> listA);
    }
    @FunctionalInterface
    interface Fb{
        Fc op(List<Integer> listB);
    }
    @FunctionalInterface
    interface Fc{
        List<Integer> op(List<Integer> listC);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1); list1.add(2); list1.add(3);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(4); list2.add(5); list2.add(6);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(7); list3.add(8); list3.add(9);

        // 1 柯里化改造前
        F f = (listA, listB, listC) -> {
            ArrayList<Integer> list = new ArrayList<>();
            list.addAll(listA);
            list.addAll(listB);
            list.addAll(listC);
            return list;
        };
        List<Integer> resultA = f.op(list1, list2, list3);
        System.out.println(resultA);


        // 2 柯里化改造后
        Fa fa = listA -> listB -> listC -> {
            ArrayList<Integer> list = new ArrayList<>();
            list.addAll(listA);
            list.addAll(listB);
            list.addAll(listC);
            return list;
        };
        Fb fb = fa.op(list1);
        Fc fc = fb.op(list2);
        List<Integer> resultB = fc.op(list3);
        System.out.println(resultB);

        // 3 柯里化的另外一种新奇（抽象）写法
        List<Integer> resultC = step3(step2(step1(fa)));
        System.out.println(resultC);
    }

    public static Fb step1(Fa fa){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);
        return fa.op(list);
    }

    public static Fc step2(Fb fb){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4); list.add(5); list.add(6);
        return fb.op(list);
    }

    public static List<Integer> step3(Fc fc) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);list.add(8);list.add(9);
        return fc.op(list);
    }
}
