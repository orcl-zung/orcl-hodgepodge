package com.orcl.hashcode.hash;

import java.util.*;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-23 10:47
 * @history: 2022-07-23 10:47 created by orcl
 */
public class HashCode {

    /**
     * 仿写 String 的 hashCode ，将固定乘积换成可变参数
     *
     * @param str        字符串
     * @param multiplier 乘积
     * @return hashcode
     */
    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * 使用不同碰撞因子计算字符串的 hashcode 并统计
     *
     * @param strList     需要计算 hashcode 字符串集合
     * @param multipliers 碰撞因子
     * @return 返回统计集合
     */
    public static List<RateInfo> collisionRateList(Set<String> strList, Integer... multipliers) {
        List<RateInfo> rateInfoList = new ArrayList<>();
        for (Integer multiplier : multipliers) {
            List<Integer> hashCodeList = new ArrayList<>();
            strList.forEach(str -> {
                Integer hashCode = hashCode(str, multiplier);
                hashCodeList.add(hashCode);
            });
            rateInfoList.add(hashCollisionRate(multiplier, hashCodeList));
        }
        return rateInfoList;
    }

    /**
     * 封装统计结果
     *
     * @param multiplier   碰撞因子
     * @param hashCodeList hashcode 集合
     * @return 封装结果对象
     */
    public static RateInfo hashCollisionRate(Integer multiplier, List<Integer> hashCodeList) {
        // 最大 hash
        Integer maxHash = hashCodeList.stream().max(Integer::compareTo).get();

        // 最小 hash
        Integer minHash = hashCodeList.stream().min(Integer::compareTo).get();

        // 碰撞数
        int collisionCount = (int) (hashCodeList.size() - hashCodeList.stream().distinct().count());

        // 碰撞比率
        double collisionRate = (collisionCount * 1.0) / hashCodeList.size();

        return new RateInfo(maxHash, minHash, multiplier, collisionCount, collisionRate);
    }

    public static Map<Integer, Integer> hashArea(Set<String> strSet, Integer multiplier) {
        List<Integer> hashCodeList = new ArrayList<>();
        strSet.forEach(str -> {
            Integer hashCode = hashCode(str, multiplier);
            hashCodeList.add(hashCode);
        });
        return hashArea(hashCodeList);
    }

    public static Map<Integer, Integer> hashArea(List<Integer> hashCodeList) {
        Map<Integer, Integer> statistics = new LinkedHashMap<>();
        int start = 0;
        for (long i = 0x80000000 ; i <= 0x7fffffff ; i += 67108864) {
            long min = i;
            long max = min + 67108864;

            int num = (int) hashCodeList.parallelStream().filter(x -> x >= min && x < max).count();
            statistics.put(start++, num);
        }
        return statistics;
    }

}
