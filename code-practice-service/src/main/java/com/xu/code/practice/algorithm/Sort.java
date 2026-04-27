package com.xu.code.practice.algorithm;

import java.util.Arrays;

public class Sort {

    /**
     * @Author liberty
     * @Date 2024/11/8 14:53
     */

    // 波浪排序
    public static void wiggleSort(int[] nums){
        int n = nums.length;
        for(int i = 1; i < n; i++){
             if((i % 2 == 1 && nums[i] < nums[i-1]) || (i % 2 == 0 && nums[i] > nums[i-1])){
                 int temp = nums[i];
                 nums[i] = nums[i-1];
                 nums[i-1] = temp;
             }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }


}
