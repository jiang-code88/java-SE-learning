# java-SE-learning
my java SE learning project

# project contents
```text
java-SE-learning
├── README.md
├── pom.xml
└── src
    └── main
        ├── java
        │ └── com
        │     └── learn
        │         ├── _01_reflection           # Java 反射
        │         │ ├── Student.java           # 反射使用
        │         │ ├── _01_ReflectDemo.java   # 反射 API
        │         │ └── _02_ReflectUsage.java  # 反射使用（运行时操作类、破坏封装、破坏泛型）
        │         ├── _02_annotation           # Java 注解 
        │         │ └── AnnotationDemo.java    # 注解作用（编译约束和成分标记）+ 自定义注解（注解属性+元注解+自定义注解解析流程）
        │         ├── _03_generic              # Java 泛型
        │         │ ├── _01_GenericUsage.java  # 泛型作用（约束数据类型避免转换异常）
        │         │ ├── _02_GenericClass.java  # 自定义泛型类 
        │         │ ├── _03_GenericMethod.java # 自定义泛型方法
        │         │ ├── _04_GenericInterface.java # 自定义泛型接口
        │         │ └── _05_GenericExpand.java # 泛型通配符
        │         ├── _04_functionalProgramming # Java 函数式编程
        │         │ ├── _01_FunctionDefinition.java # 合格函数的定义
        │         │ ├── _02_FunctionObject.java     # 函数对象的定义
        │         │ ├── _03_FunctionObjectParameterization.java # 函数对象参数化
        │         │ ├── _04_FunctionObjectExpression.java # 函数对象的表现形式（lambda表达式、方法引用）
        │         │ ├── _05_FunctionObjectType.java       # JDK内置函数式接口 + 自定义函数式接口
        │         │ ├── _06_FunctionObjectMethodReference.java  # 函数对象的方法引用表现形式详解
        │         │ ├── _07_FunctionObjectClosure.java    # 函数式编程的闭包（给函数对象提供除参数以外其他的数据）
        │         │ └── _08_FunctionObjectCurrying.java   # 函数式编程的柯里化
        │         └── _05_streamAPI             # Java 流式编程
        │             ├── _01_Iterator.java     # 集合流式编程和迭代器遍历的区别 + 流式编程的使用方式
        │             ├── _02_CreateStream.java # Stream 流的获取方式（单列集合、双列集合、数组、零散数据（数据的类型要相同）、生成流）
        │             ├── _03_MiddleOperation.java # Stream 流的中间操作
        │             ├── _04_TerminationOperation.java # Stream 流的终止操作
        │             └── _05_BasicStream.java # Stream 流的基本流（基本数据类型元素的流）
        └── resources

```
# TODO