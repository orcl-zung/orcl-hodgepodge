package com.orcl.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-13 12:28
 * @history: 2022-07-13 12:28 created by orcl
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(7);
        map.put(null, "1");
        map.put("2", "1");
        map.put("3", "1");
    }

}
