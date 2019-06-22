package com.yjm.study.test.search;

/**
 * Created by test on 16/6/1.
 */
public class Search {

    public static void main(String[] args){
        int[] a = new int[]{1,2,4,5,6,7,8,9,10};
        int result = binarySearch(a, 0, a.length-1, 3);
        System.out.println("result=" + result);
    }

    /**
     * 二分查找:递归
     * @param a
     * @param low
     * @param high
     * @param dest
     * @return
     */
    public static int binarySearch(int[] a, int low, int high, int dest){
        int mid = low + (high-low)/2;
        if(a[mid] == dest){
            // 中值正好等于目标值，则返回
            return mid;
        }else if(low >= high){
            // 如果低位和高位两个指针相等了还没匹配到，说明序列中不存在目标值，返回-1表示没有匹配项
            return -1;
        }else {
            int result;
            if(a[mid] > dest){
                // 对左边的子数组递归搜索
                result = binarySearch(a, low, mid-1, dest);
            }else {
                // 对右边的子数组递归搜索
                result = binarySearch(a, mid+1, high, dest);
            }
            return result;
        }
    }

    /**
     * 二分查找:非递归
     * @param a
     * @param dest
     * @return
     */
    public static int binarySearch(int[] a, int dest){
        int low = 0, high = a.length-1, mid;
        while(low <= high){
            mid = low + (high-low)/2;
            if(a[mid] == dest){
                return mid;
            }else if(a[mid] > dest){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return -1;
    }
}
