package com.learn._07_lombok;

/**
 * lombok 类库实践指南
 */
public class LombokMain {
    /* 简化 java 中的模版代码书写，自动生成模版代码 */

    /**
     * 构造函数
     * - @AllArgsConstructor 为类中所有成员变量生成一个构造方法
     * - @NoArgsConstructor 为类生成一个无参的构造方法
     *
     * - @AllArgsConstructor(access = xxx) access 参数用于控制生成的构造方法的访问权限关键字
     *
     * - 如果成员变量是 final 修饰的说明该成员变量必须要在第一次初始化时赋值后续将不可被赋值。
     *   那么此时 @NoArgsConstructor 将报错提示无法生成无参构造因为实例化该类时 final 成员变量需要被赋值的。
     *   这时候可以使用 @NoArgsConstructor(force = true) 强制其在生成的无参构造方法中为 final 成员变量
     *   赋值其数据类型的默认值；
     *   也可以使用 @RequiredArgsConstructor 注解，仅对必须要赋值的成员变量如 final 成员变量
     *   生成其对应构造方法（如果没有必须要赋值的成员变量则生成无参构造方法）
     */

    /**
     * getter / setter 方法
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
     *   判断的依据为成员变量所对应 getter/setter 方法名的方法是否已存在，
     *   如成员变量 name 手动写了 getName / setName 方法后，lombok 将不会再生成方法。
     * - 注意：如果存在 setName 方法但是和真正的 setName 方法形参是不同的（属于重载方法），
     *   lombok 也不会再生成 setName 方法(由于方法名匹配的误判，导致不会生成真实的 setter 方法)
     *   这个时候只需要在 setName（重载方法）上添加 @Tolerate 注解
     *   就可以实现让 lombok 忽略这个手写的 setName（重载方法）继续生成正确的 setter 方法
     */

    /**
     * toString 方法
     * - @ToString 生成类中所有字段的 toString 方法
     *
     * 注解参数功能
     *  - includeFieldNames 控制类打印的 toString 中是否包含字段名（默认是 true）
     *  - callSuper 控制是否同时调用父类的 toString 方法打印（默认是 false）
     *  - doNotUseGetters 生成的 toString 方法默认是通过 getter 方法获取成员变量的值，
     *    该参数控制通过 getter 方法获取，还是直接访问成员变量（默认是使用 getter）
     *  - onlyExplicitlyIncluded 控制类中所有成员变量时全部打印还是通过 Include 注解控制局部打印（默认是 false 表示全部打印）
     *  - @ToString.Exclude 控制成员变量不被打印，默认是全部成员变量都打印。
     *  - @ToString.Include 控制成员变量/成员方法被打印，在 onlyExplicitlyIncluded=false 也生效的，主要是生效其注解内部的 rank 和 name 功能
     *      - rank 变量用于设置成员变量打印的优先级（默认是按成员变量定义顺序打印），
     *        所有变量的 rank 默认都是 0，变量的 rank 值定义越大越优先打印。
     *      - name 变量用于自定义成员变量打印时的字段名。
     *      - @ToString.Include 使用在方法时是控制 toString 的结果包含该方法的返回结果打印（打印的字段名为方法名）
     *  控制打印成员变量的范围：
     *   - 黑名单模式：默认是类中所有成员变量全部打印，在成员变量上标记 @ToString.Exclude 控制该成员变量不打印。
     *   - 白名单模式：类上标记 @ToString(onlyExplicitlyIncluded = true) + 成员变量标记 @ToString.Include
     *     控制只打印类中的那些成员变量。
     */

    /**
     * equals 和 hashCode 方法
     *  - @EqualsAndHashCode 注解生成类的 equals 和 hashCode 方法
      */

    /**
     * 使用 @Data 注解相当于添加了
     * @Getter
     * @Setter
     * @RequiredArgsConstructor
     * @ToString()
     * @EqualsAndHashCode
     * 注意：这里是没有添加 @AllArgsConstructor 注解的，所以需要所有成员变量的构造函数时请自行加上。
     */

    /**
     * 建造者模式
     *  - 使用 @Builder 注解实现建造者模式（用于更优雅创建类实例的设计模式），实际是自动生成该类的 builder 类和 builder 方法（用于获取 builder 类实例）。
     *  - 使用方式：类.builder() 获取该类的 builder 类实例，然后通过链式调用的方式设置实例中成员变量的值，最后 builder 类实例.build() 方法获取实例对象。
     *
     * 注解参数功能
     *  - toBuilder 控制是否生成 toBuilder 方法（默认是 false 不生成）
     *    toBuilder 方法用于将类的实例对象回炉重造回 builder 对象，
     *    实现基于原对象的内容使用建造者模式修改内容重新生成新对象。
     *  - @Builder.Default 标记在类成员变量上，控制 builder 类成员变量的默认值使用类成员变量定义的默认值。
     */

    /**
     * lombok 实验性功能
     *
     * - @FieldDefaults 标记在类上自动为类中所有成员变量设置访问权限关键字
     *   例如 @FieldDefaults(level = AccessLevel.PRIVATE) 将自动为所有的字段设置 private 的访问权限关键字
     *
     * - @StandardException 标记在自定义异常类上为类自动生成调用父类的异常构造方法
     *    用于传递 message 和 cause 异常信息构建异常类实例。
     */

    public static void main(String[] args) {
        Account account = new Account(1, "小明", 23, "男性", "xxx", "我是学生", false);
        System.out.println(account);

        // builder 建造器模式构建类示例对象使用示例
        Account accountNew = Account.builder()
                .id(1)
                .age(40)
                .name("老明")
                .build();
        System.out.println(accountNew);

        // builder 建造器模式的 toBuilder 从类对象转 builder 类对象使用示例
        Account.AccountBuilder builder = account.toBuilder();
        Account accountVip = builder.vip(true).build();
        System.out.println(accountVip);


        // 为自定义异常类生成重写父类的构造函数
        AccountException accountException = new AccountException();
    }

}
