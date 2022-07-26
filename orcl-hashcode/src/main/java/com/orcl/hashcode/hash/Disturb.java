package com.orcl.hashcode.hash;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-23 16:10
 * @history: 2022-07-23 16:10 created by orcl
 */
public class Disturb {

    /**
     * 计算某个字符串扰动之后的 hashcode
     *
     * @param key  字符串
     * @param size 数组长度
     * @return 扰动后的 hashcode
     */
    public static int disturbHashIdx(String key, int size) {
        int h;
        return (size - 1) & ((h = key.hashCode()) ^ (h >>> 16));
    }

    /**
     * 计算某个字符串没有扰动的 hashcode
     *
     * @param key  字符串
     * @param size 数组长度
     * @return 没有扰动的 hashcode
     */
    public static int hashInx(String key, int size) {
        return (size - 1) & key.hashCode();
    }

}
