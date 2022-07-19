package com.orcl.reflection.anno;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 10:13
 * @history: 2022-07-19 10:13 created by orcl
 */
public class AnnotationTest {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Class<AnnotationDemo> clazz = AnnotationDemo.class;

        Method method = clazz.getMethod("test");
        AnnoTest methodAnno = method.getAnnotation(AnnoTest.class);
        System.out.println(methodAnno.value());

        AnnoTest clazzAnno = clazz.getAnnotation(AnnoTest.class);
        System.out.println(clazzAnno.value());

        AnnoTest fieldAnno = clazz.getDeclaredField("name").getAnnotation(AnnoTest.class);
        System.out.println(fieldAnno.value());


    }

}
