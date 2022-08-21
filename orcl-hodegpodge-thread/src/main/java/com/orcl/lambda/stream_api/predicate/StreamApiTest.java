package com.orcl.lambda.stream_api.predicate;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-12 14:43
 * @history: 2022-08-12 14:43 created by orcl
 */
public class StreamApiTest {

    public static void main(String[] args) {
        Person person = new Person("orcl", 20);
        MyPredicateImpl predicate = new MyPredicateImpl();
        myPrint(person, predicate);
    }

    private static void myPrint(Person person, MyPredicate<Person> filter) {
        System.out.println(filter.test(person));
    }

}
