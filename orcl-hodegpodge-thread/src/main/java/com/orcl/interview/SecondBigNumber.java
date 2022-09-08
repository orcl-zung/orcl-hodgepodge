package com.orcl.interview;

/**
 * @description:
 * @author: orcl
 * @since: 2022-09-08 17:13
 * @history: 2022-09-08 17:13 created by orcl
 */
public class SecondBigNumber {

    public static void main(String[] args) {
        int second = calSecondBigNum(new int[]{999, 999, 4, 5, 6, 7, 3, 4, 90, 100, 22, 3, 102, 11, 23});
    }

    public static int calSecondBigNum(int[] arr) {
        int max = 0;
        int secondMax = 0;

        for (int item : arr) {
            if (item > max) {
                secondMax = max;
                max = item;

                continue;
            }

            if (item > secondMax && item != max) {
                secondMax = item;
            }
        }

        System.out.println("最大的数：" + max);
        System.out.println("第二大的数：" + secondMax);
        return secondMax;
    }

}
