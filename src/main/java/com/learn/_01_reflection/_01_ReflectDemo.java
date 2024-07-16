package com.learn._01_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射、注解、代理、泛型是 Java 的高级技术
 *
 * 反射是对于任何一个类，在「运行时」获取类的全部成分信息，信息来源是「类编译后的字节码 class 文件对象」。
 *  - 类的构造器对象    java.lang.reflect.Constructor
 *  - 类的成员变量对象  java.lang.reflect.Field
 *  - 类的成员方法对象  java.lang.reflect.Method
 *  - 类字节码 class 文件对象  java.lang.Class
 *
 * 反射使用 API
 * 1 获取类字节码 class 文件对象（也称类的类对象）
 *   1）类名.class
 *   2）对象.getClass()
 *   3）Class.forName("类的全限定名") 「类加载的方式」
 * 2 获取类的构造器对象
 *   1）getConstructor 根据参数匹配获取某个构造器（只能拿 public 修饰的构造器）
 *   2）getDeclaredConstructor 根据参数匹配获取某个构造器（无需关心权限修饰符，只要声明了就可获取）
 *   3）getConstructors 获取所有的构造器（只能拿 public 修饰的构造器）
 *   4）getDeclaredConstructors 获取所有的构造器（无需关心权限修饰符，只要声明了就可获取）
 * 3 通过获取的类构造器对象实例化类的对象
 *   1）setAccessible 修改构造器方法的访问权限（一次性修改，每次使用均需修改一次），
 *      true 代表即使 private 也能暴力实例化，false 代表保留原有的访问权限。
 *   2）newInstance 通过构造器方法创建对象，方法参数传递调用构造器方法需要传递的参数
 * 4 获取类的成员变量
 *   1）getField 根据成员变量名获取对应的 Field 对象（只能获取 public 修饰的）
 *   2）getDeclaredField 根据成员变量名获取对应的 Field 对象（获取所有已声明的，不管访问修饰符）
 *   3）getFields 获取所有成员变量对应的 Field 对象（只能获取 public 修饰的）
 *   4）getDeclaredFields 获取所有成员变量对应的 Field 对象（获取所有已声明的，不管访问修饰符）
 * 5 通过获取的类成员变量 Field 对象操作对象的成员变量值
 *   1）set(Object obj, Object value) 给对象注入某个成员变量的值
 *   2）get(Object obj) 获取对象某个成员变量的值
 *   3）getName 获取成员变量的名称
 *   4）getType 获取成员变量的类型
 *   5）setAccessible 修改成员变量的访问权限，true 代表可以访问私有的成员变量，
 * 6 获取类的成员方法
 *   1）getMethod 根据方法名和方法参数类型获取对应的方法对象（只能获取 public 修饰的）
 *   2）getDeclaredMethod 根据方法名和方法参数类型获取对应的方法对象（获取所有已声明的，不管访问修饰符）
 *   3）getMethods 获取所有成员方法对象（只能获取 public 修饰的，且包括从父类中继承的方法）
 *   4）getDeclaredMethods 获取所有成员方法对象（获取所有声明的，不管修饰符，只能返回本类中声明的，不包含从父类继承的方法）
 * 7 通过获取的类成员方法对象，操作对象方法的调用
 *   1）invoke(Object obj, Object... args) 指定调用方法的对象，传递调用方法的参数
 */
public class _01_ReflectDemo {
    public static void main(String[] args) throws Exception {
        /* 1 获取类字节码 class 文件对象（也称类的类对象） */
        // 1）类名.class
        Class<?> studentClass = Student.class;
        System.out.println(studentClass);

        // 2）对象.getClass()
        Student student = new Student();
        Class<?> studentClass_A = student.getClass();
        System.out.println(studentClass_A);

        // 3）Class.forName("类的全限定名")
        // 类加载器直接通过「类的全限定名」加载对应类的字节码文件到内存中封装到 class 文件对象中。
        Class<?> studentClass_B =
                Class.forName("com.learn._01_reflection.Student");
        System.out.println(studentClass_B);

        System.out.println(studentClass_B.getSimpleName()); // 从类 class 文件对象获取类名
        System.out.println(studentClass_B.getName());       // 从类 class 文件对象获取类的全限定名
        System.out.println("--------------------------------");


        /* 2 获取类的构造器对象 */
        Constructor<?> constructor = studentClass.getConstructor();
        Constructor<?> constructorWithParam = studentClass.getConstructor(int.class, String.class, String.class);
        Constructor<?>[] constructors = studentClass.getConstructors();
        Constructor<?> declaredConstructor = studentClass.getDeclaredConstructor();
        Constructor<?>[] declaredConstructors = studentClass.getDeclaredConstructors();
        System.out.println(constructorWithParam);
        System.out.println("--------------------------------");


        /* 3 通过获取的类构造器对象实例化类的对象 */
        declaredConstructor.setAccessible(true); // 把私有构造器的权限打开，实现暴力实例化对象（一次性的修改）
        Student studentInstance = (Student) declaredConstructor.newInstance();
        System.out.println(studentInstance);
        System.out.println("--------------------------------");


        /* 4 获取类的成员变量 */
        Field addrB = studentClass.getDeclaredField("addr");
        Field[] fields = studentClass.getFields();
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName() + " : " + declaredField.getType());
        }
        System.out.println("--------------------------------");


        /* 5 通过获取的类成员变量 Field 对象操作对象的成员变量值 */
        Student studentField = new Student();
        Field nameField = studentClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(studentField, "paul");
        System.out.println(studentField);
        System.out.println(nameField.get(studentField));
        System.out.println(nameField.getName());
        System.out.println(nameField.getType());
        System.out.println("--------------------------------");


        /* 6 获取类的成员方法 */
        Method method = studentClass.getDeclaredMethod("getId");
        // 获取 public 修饰方法同时会获取从父类中继承的方法
        Method[] methods = studentClass.getMethods();
        // 获取所有声明的方法，不关心访问权限，无法获取从自父类中继承的方法
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        System.out.println("====");
        for (Method m : declaredMethods) {
            System.out.println(m.getName());
        }
        System.out.println("--------------------------------");


        /* 7 通过获取的类成员方法对象，操作对象方法的调用 */
        Student studentInvoke = new Student(200, "aaa", "bbb");
        Method getId = studentInvoke.getClass().getMethod("getId");
        Method setId = studentInvoke.getClass().getMethod("setId", int.class);
        Object resultGetId = getId.invoke(studentInvoke);
        System.out.println(resultGetId);
        Object resultSetId = setId.invoke(studentInvoke, 300);
        System.out.println(resultSetId); // 调用没有返回值的方法将返回 null
        System.out.println(studentInvoke);
        System.out.println("--------------------------------");

    }

}
