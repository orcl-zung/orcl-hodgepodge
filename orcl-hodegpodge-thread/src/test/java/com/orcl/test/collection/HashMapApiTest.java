package com.orcl.test.collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-24 21:06
 * @history: 2022-08-24 21:06 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashMapApiTest {

    private final Map<String, Integer> map = new HashMap<>();

    @Before
    public void init() {
        map.put("Tom", 22);
        map.put("David", 20);
        map.put("Lily", 20);
        map.put("Lucy", 20);
        map.put("James", 21);
        map.put("Jim", 21);
    }

    @Test
    public void test_hashMap_entrySet_for() {
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            printKeyAndValue(entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void test_hashMap_keySet_for() {
        Set<String> keySet = map.keySet();
        for (String k : keySet) {
            printKeyAndValue(k, map.get(k));
        }
    }

    @Test
    public void test_hashMap_foreach() {
        map.forEach(this::printKeyAndValue);
    }

    @Test
    public void test_hashMap_iterator() {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            printKeyAndValue(entry.getKey(), entry.getValue());
        }
    }

    public void printKeyAndValue(String k, Integer v) {
        System.out.println("Key = " + k + ", Value = " + v);
    }

}
