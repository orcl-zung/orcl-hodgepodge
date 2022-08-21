package com.orcl.lambda.stream_api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description:
 * @author: orcl
 * @since: 2022-08-12 17:19
 * @history: 2022-08-12 17:19 created by orcl
 */
public class ConvertUtil {

    private ConvertUtil() {
    }

    /**
     * 将List转为Map
     *
     * @param list         原数据
     * @param keyExtractor Key的抽取规则
     * @param <K>          Key
     * @param <V>          Value
     * @return map
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<K, V> map = new HashMap<>(list.size());
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            map.put(k, v);
        }
        return map;
    }

    /**
     * 将List转为Map，可以指定过滤规则
     *
     * @param list         原数据
     * @param keyExtractor Key的抽取规则
     * @param predicate    过滤规则
     * @param <K>          Key
     * @param <V>          Value
     * @return map
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor, Predicate<V> predicate) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<K, V> map = new HashMap<>(list.size());
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null || !predicate.test(v)) {
                continue;
            }
            map.put(k, v);
        }
        return map;
    }

    /**
     * 将 List 映射为 List ，比如 List<Person> personList 转为 List<String> nameList
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return 新 list
     */
    public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }

        List<R> newList = new ArrayList<>();

        for (T t : originList) {
            R r = mapper.apply(t);
            newList.add(r);
        }
        return newList;
    }

    /**
     * 将 List 映射为 List，比如 List<Person> personList 转为 List<String> nameList
     * 可以指定过滤规则
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param predicate  过滤规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return 新 list
     */
    public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper, Predicate<T> predicate) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>();

        for (T t : originList) {
            if (predicate.test(t)) {
                R apply = mapper.apply(t);
                newList.add(apply);
            }
        }
        return newList;
    }

}
