package com.orcl.argorithm;

/**
 * @description: 二分查找算法
 * @author: orcl
 * @since: 2023-02-19 15:54
 * @history: 2023-02-19 15:54 created by orcl
 */
public class BinarySearch {

    public static void main(String[] args) {
        int result = binarySearch(new int[]{1, 4, 5, 43, 55, 66, 67, 69, 80, 100, 200}, 100);
        System.out.println(result);
    }

    static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target == arr[mid]) {
                return target;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

}
