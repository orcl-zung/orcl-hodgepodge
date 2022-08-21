package com.orcl.lambda.stream_api.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 自定义 predicate 函数式接口
 * @author: orcl
 * @since: 2022-08-12 14:35
 * @history: 2022-08-12 14:35 created by orcl
 */
@FunctionalInterface
interface MyPredicate<T> {

    /**
     * 定义了一个test()方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
     *
     * @param t 任意对象
     * @return boolean 值
     */
    boolean test(T t);

}

class MyPredicateImpl implements MyPredicate<Person> {
    @Override
    public boolean test(Person person) {
        return person.getAge() > 19;
    }
}

@Data
@AllArgsConstructor
class Person {

    private String name;

    private Integer age;

}
