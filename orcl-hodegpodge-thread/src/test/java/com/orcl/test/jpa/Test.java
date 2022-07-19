package com.orcl.test.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 14:14
 * @history: 2022-07-19 14:14 created by orcl
 */
public class Test {

    public static void main(String[] args) {
        new B();
    }

}

class A<T> {

    public A() {
        // this 指代的是子类
        Class<? extends A> clazz = this.getClass();

        // 得到泛型父类
        Type genericSuperclass = clazz.getGenericSuperclass();

        // 本质是ParameterizedTypeImpl，可以向下强转
        ParameterizedType parameterizedTypeSuperClass = (ParameterizedType) genericSuperclass;

        // 强转后可用的方法变多了，比如getActualTypeArguments()可以获取Class A<String>的泛型的实际类型参数
        Type[] actualTypeArguments = parameterizedTypeSuperClass.getActualTypeArguments();

        // 由于A类只有一个泛型，这里可以直接通过actualTypeArguments[0]得到子类传递的实际类型参数
        Type actualTypeArgument = actualTypeArguments[0];
        System.out.println(actualTypeArgument.getClass());
        System.out.println(actualTypeArgument.getTypeName());

//        System.out.println(genericSuperclass.getClass());
//        System.out.println(genericSuperclass.getTypeName());
//        System.out.println(genericSuperclass);
    }
}

class B extends A<String> {

}

class C extends A<String> {

}
