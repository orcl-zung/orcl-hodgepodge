package com.orcl.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-13 11:45
 * @history: 2022-07-13 11:45 created by orcl
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> list1 = new ArrayList<>(list);


    }

}
