package com.learn._04_functionalProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 函数对象的行为参数化
 */
public class _03_FunctionObjectParameterization {
    static class Student{
        public String name;
        public Integer age;
        public String sex;
        public Student(String name, Integer age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("张无忌", 18, "男"));
        students.add(new Student("杨不悔", 16, "女"));
        students.add(new Student("周芷若", 19, "女"));
        students.add(new Student("宋青书", 20, "男"));

        // 1 常规写法
        System.out.println(filterSex(students));
        System.out.println(filterAge(students));
        System.out.println("---------------------------");

        // 2 使用「函数对象」实现「函数行为参数化」改造写法
        //   将 filterSex() 和 filterAge() 进行重复代码的提取合并，实现更加通用的函数
        Lambda filterSexLambda = student -> "男".equals(student.sex);
        System.out.println(filter(students, filterSexLambda));
        System.out.println(filter(students, student -> student.age > 18));
    }

    // 筛选「性别为男」的元素
    public static List<Student> filterSex(List<Student> students){
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students){
            if ("男".equals(student.sex)){
                result.add(student);
            }
        }
        return result;
    }

    // 筛选「年龄大于 18 岁」的元素
    public static List<Student> filterAge(List<Student> students){
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students){
            if (student.age > 18){
                result.add(student);
            }
        }
        return result;
    }

    public interface Lambda {
        boolean test(Student student);
    }

    public static List<Student> filter(List<Student> students, Lambda lambda){
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students){
            if (lambda.test(student)){
                result.add(student);
            }
        }
        return result;
    }
}
