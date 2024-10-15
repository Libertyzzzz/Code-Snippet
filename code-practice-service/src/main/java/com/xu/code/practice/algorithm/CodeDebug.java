package com.xu.code.practice.algorithm;

import java.util.*;

public class CodeDebug {
    private static final int num = 1;
    private int code = 1;

    /**
     * @Author liberty
     * @Date 2024/10/13 16:06
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> repeated = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (repeated.contains(nums[i]))
                continue;
            int subSum = 0 - nums[i];
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length - 1; j++) {
                while ((j < k) && (nums[j] + nums[k] > subSum)) {
                    k--;
                }
                if (nums[j] + nums[k] == subSum) {
                    List<Integer> curr = new ArrayList<>();
                    curr.add(nums[i]);
                    curr.add(nums[j]);
                    curr.add(nums[k]);
                    res.add(curr);
                }
            }
            repeated.add(nums[i]);
        }
        return res;
    }
    /*
        滑动窗口通用模板
        //外层循环扩展右边界，内层循环扩展左边界
        for (int l = 0, r = 0 ; r < n ; r++) {
        //当前考虑的元素
        while (l <= r && check()) {//区间[left,right]不符合题意
            //扩展左边界
        }
        //区间[left,right]符合题意，统计相关信息
        }

     */

    public static void customizeSort() {
        List<Integer> list = new ArrayList<>();
        list.add(-5);
        list.add(-7);
        list.add(3);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);

    }

    // 旋转矩阵打印
    public static List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        List<Integer> res = new ArrayList<>();
        while (left <= right && top <= bottom) {
            // 往右遍历
            for (int i = left; i <= right; i++) {
                res.add(matrix[left][i]);
            }
            top++;
            // 往下遍历
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            // 往左遍历
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 往上遍历
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }

        }
        return res;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum >= target) {
                return n - i;
            }

        }

        return 0;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        if (n < 2)
            return n;
        int left = 0;
        Set<Character> window = new HashSet<>();
        int maxLength = 0;
        // 遍历字符串，right 是滑动窗口的右边界
        for (int right = 0; right < s.length(); right++) {
            // 如果当前字符已经在窗口中，移动左边界以去重
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            // 将当前字符添加到窗口中
            window.add(s.charAt(right));
            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }
//        while ((right < n)) {
//            if (window.contains(s.charAt(right))) {
//                window.remove(s.charAt(left));
//                left++;
//            }
//            window.add(s.charAt(right));
//            res = Math.max(res, window.size());
//            right++;
//        }
//        return res;
        return maxLength;

    }

    // 旋转图像
    public static void rotate(int[][] matrix) {
        // int n = matrix.length;
        // int[][] rotatedMatrix = new int[n][n];
        // for(int i = 0; i < n; i++){
        //     for(int j = n - 1; j >= 0; j--){
        //         rotatedMatrix[i][n -1 -j] = matrix[j][i];
        //     }
        // }


        // for(int i = 0; i < n; i++){
        //     // System.out.println(Arrays.toString(rotatedMatrix[i]));
        //     matrix[i] = Arrays.copyOf(rotatedMatrix[i], n);
        // }


        // 优化
        // 1. 沿着中轴线水平翻转
        // 2. 对角线翻转
        optimized(matrix);


    }

    public static void optimized(int[][] matrix){
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++){
            for(int j = 0; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = temp;
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int target = 213;
        String s = "qrsvbspk";
        // threeSum(nums);
        // minSubArrayLen(target, nums);
        // lengthOfLongestSubstring(s);
        // customizeSort();
        spiralOrder(matrix);

        int[] arr = Arrays.copyOf(nums, 12);
        System.out.println(Arrays.toString(arr));

    }

}
