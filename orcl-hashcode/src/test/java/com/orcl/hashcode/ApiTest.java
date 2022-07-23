package com.orcl.hashcode;

import com.orcl.hashcode.hash.FileUtil;
import com.orcl.hashcode.hash.HashCode;
import com.orcl.hashcode.hash.RateInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-23 10:37
 * @history: 2022-07-23 10:37 created by orcl
 */
public class ApiTest {

    private Set<String> words;

    @Before
    public void before() {
        // 读取文件，103976 个英文单词库
        words = FileUtil.readWordList("D:/IdeaProjects/interview/interview-03/103976个英语单词库.txt");
    }

    @Test
    public void test_collisionRate() {
        System.out.println("单词数量：" + words.size());
        Integer[] multipliers = {2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199};
        List<RateInfo> rateInfoList = HashCode.collisionRateList(words, multipliers);
        for (RateInfo rate : rateInfoList) {
            System.out.printf("乘数 = %4d, 最小Hash = %11d, 最大Hash = %10d, 碰撞数量 =%6d, 碰撞概率 = %.4f%%%n", rate.getMultiplier(), rate.getMinHash(), rate.getMaxHash(), rate.getCollisionCount(), rate.getCollisionRate() * 100);
        }
    }

    @Test
    public void test_hashArea() {
        System.out.println(HashCode.hashArea(words, 2).values());
        System.out.println(HashCode.hashArea(words, 3).values());
        System.out.println(HashCode.hashArea(words, 5).values());
        System.out.println(HashCode.hashArea(words, 7).values());
        System.out.println(HashCode.hashArea(words, 17).values());
        System.out.println(HashCode.hashArea(words, 31).values());
        System.out.println(HashCode.hashArea(words, 32).values());
        System.out.println(HashCode.hashArea(words, 33).values());
        System.out.println(HashCode.hashArea(words, 39).values());
        System.out.println(HashCode.hashArea(words, 41).values());
        System.out.println(HashCode.hashArea(words, 199).values());
    }

    @Test
    public void test_cal() {
        String str = "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 103910, 34, 0, 18, 0, 0, 7, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 0";
        String[] split = str.split(",");
        System.out.println(split.length);
    }

}
