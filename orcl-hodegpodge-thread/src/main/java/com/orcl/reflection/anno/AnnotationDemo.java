package com.orcl.reflection.anno;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-19 09:43
 * @history: 2022-07-19 09:43 created by orcl
 */
@AnnoTest("annotation for class")
public class AnnotationDemo {

    @AnnoTest("annotation for field")
    private String name;

    @AnnoTest("annotation for method")
    public void test() {
        System.out.println("test something...");
    }

}
