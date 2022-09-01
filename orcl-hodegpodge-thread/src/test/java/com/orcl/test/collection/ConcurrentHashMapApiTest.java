package com.orcl.test.collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-25 15:07
 * @history: 2022-08-25 15:07 created by orcl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrentHashMapApiTest {

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
    public void test_ConcurrentHashMap_api() {

        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(map);
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    }

}
