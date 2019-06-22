package com.yjm.study.test.sort;

/**
 * 排序算法
 *
 * Created by test on 16/6/1.
 */
public class Sort {

    public static void main(String[] args){
        int[] a = new int[]{5,3,10,7,2,8,4,-1,6,9,1,0};
        quickSort(a, 0, a.length-1);
        print(a);
    }

    /**
     * 交换排序:冒泡排序
     * @param a
     */
    public static void bubbleSort(int[] a){
        int length = a.length;
        if(length > 1){
            for(int i = 0; i < length-1; i++){
                for(int j = 0; j < length-i-1; j++){
                    if(a[j] > a[j+1]){
                        int temp = a[j+1];
                        a[j+1] = a[j];
                        a[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 交换排序:快速排序
     * @param a
     */
    public static void quickSort(int[] a, int low, int high){
        if(low < high){
            // 一趟交换排序,得到中值位置
            int temp = partition(a, low, high);
            // 递归地对中值左侧的子数组交换排序
            quickSort(a, low, temp-1);
            // 递归地对中值右侧的子数组交换排序
            quickSort(a, temp+1, high);
        }
    }

    /**
     * 分区:一趟交换排序
     * @param a
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] a, int low, int high){
        int mark = a[low];
        // 移动高位低位的指针,一趟下来,可将比mark大的数都移动到它的右侧,比mark小的数都移动到它的左侧
        while(low < high){
            // 移动高位指针,直到找到比mark小的数
            while(mark <= a[high] && low < high){
                high--;
            }
            if(low == high){
                break;
            }
            // 将高位比mark小的数移动到低位
            a[low++] = a[high];
            // 移动低位指针,直到找到比mark大的数
            while(mark >= a[low] && low < high){
                low++;
            }
            if(low == high){
                break;
            }
            // 将低位比高位大的数移动到高位
            a[high--] = a[low];
        }
        // 将基准值mark放置到中间值位置
        a[low] = mark;
        return low;
    }

    private static void print(int[] a){
        for(int item : a){
            System.out.print(item + ", ");
        }
        System.out.println();
    }
}
