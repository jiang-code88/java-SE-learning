package com.learn._07_lombok;

/**
 * lombok 类库实践指南
 */
public class LombokMain {
    /* 简化 java 中的模版代码书写，自动生成模版代码 */
    /**
     * 构造函数
     *
     */

    /**
     * getter / setter
     * - 在类上使用 @Getter 注解，类所有成员变量增加 getter 方法
     * - 在成员变量上使用 @Getter 注解，仅这个成员变量增加 getter 方法
     * - @Getter(AccessLevel.PUBLIC) 对应 getter 方法使用 public 关键字（默认值）
     * - @Getter(AccessLevel.MODULE) 对应 getter 方法方法不添加任何访问权限关键字，与 PACKAGE 类似
     * - @Getter(AccessLevel.PROTECTED) 对应 getter 方法使用 protected 关键字
     * - @Getter(AccessLevel.PACKAGE) 对应 getter 方法不添加任何访问权限关键字
     * - @Getter(AccessLevel.PRIVATE) 对应 getter 方法使用 private 关键字
     * - @Getter(AccessLevel.NONE) 对应 getter 方法不生成，适合对类中不需要生成 getter 方法的字段进行排除
     *
     * - 如果已经手动编写了成员变量的 getter / setter 方法，那么 lombok 将不会生成方法进行覆盖
     *   判断依据为成员变量所对应 getter/setter 方法名的方法是否已存在，如手动写了 getName / setName 方法后，lombok 将不会再生成方法。
     * - 注意：如果存在 setName 但是和真正生成的 setName 方法形参是不同的（重载方法），lombok 也不会再生成正确的 setName (仅仅方法名匹配的误判)
     *   这个时候只需要在 setName（重载方法）上添加 @Tolerate 注解就可以实现让 lombok 忽略这个手写的 setName（重载方法）继续生成正确的 setter 方法
     */

    /**
     * toString
     *
     */

    /**
     * equals
      */

    /**
     *hashCode
     */
    public static void main(String[] args) {
        Account account = new Account();
        System.out.println(account.getId());
    }

}
