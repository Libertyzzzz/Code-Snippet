package com.xu.code.practice.algorithm;


import com.xu.code.practice.entity.ListNode;
import com.xu.code.practice.entity.TreeNode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    /*q'q'q'q'q'q'q'q'q'q'q'q'q'q'q'q'q'q'q
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

    // 矩阵置0
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j] == 1)
                    continue;
                if(matrix[i][j] == 0){
                    if(visited[i][j] == 1)
                        continue;
                    for(int k = 0; k < n; k++){
                        if(matrix[i][k] != 0){
                            matrix[i][k] = 0;
                            visited[i][k] = 1;
                        }

                    }
                    for(int k = 0; k < m; k++){
                        if(matrix[k][j] != 0){
                            matrix[k][j] = 0;

                            visited[k][j] = 1;
                        }
                    }
                }
            }
        }
    }

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        int n = s.length();
        int[] count = new int[26];
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            count[ch - 'a']++;
        }
        for(int i = 0; i < n; i++){
            char ch = t.charAt(i);
            count[ch - 'a']--;
            if(count[ch - 'a'] < 0)
                return false;
        }
        // for(int i = 0; i < 26; i++){
        //     if(count[i] != 0)
        //         return false;
        // }
        return true;
    }

    // 同构字符串--单词规律
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        Map<Character, String> patternToWords = new HashMap<>();
        Map<String, Character> wordsToPattern = new HashMap<>();
        int n = pattern.length();
        for(int i = 0; i < n; i++){
            char ch = pattern.charAt(i);
            String curr = words[i];
            if((patternToWords.keySet().contains(ch) && patternToWords.get(ch).equals(curr) ) || (wordsToPattern.keySet().contains(curr) &&wordsToPattern.get(curr) != ch )){
                return false;
            }
            patternToWords.put(ch, curr);
            wordsToPattern.put(curr, ch);

        }
        return true;
    }

    // 快乐字符串
    public static boolean isHappy(int n) {
        // 如果计算出的下一次结果在hash集中出现，说明进入了循环，不是快乐数
        Set<Integer> seen = new HashSet<>();
        while(n != 1){
            n = getNext(n);
            if(seen.contains(n))
                return false;
            seen.add(n);
        }
        return true;
    }

    // 最长连续序列
 public static int longestConsecutive(int[] nums) {
        if(nums.length < 2)
            return nums.length;
        Set<Integer> numSet = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            numSet.add(nums[i]);
        }
        int longestSeqeence = 1;
        for(int i = 0; i < nums.length; i++){
            int currSequence = 1;
            if(!numSet.contains(nums[i] - 1)){
                while(i < nums.length && numSet.contains(++nums[i]))
                    currSequence++;
            }
            longestSeqeence = Math.max(currSequence, longestSeqeence);
        }

        return longestSeqeence;
    }

    public static int getNext(int n){
        int sum = 0;
        while(n != 0){
            int r = n % 10;
            sum += r * r;
            n /= 10;
        }
        return sum;
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;

        int start = 0, end  = 0;
        while(end < n){
            while((end+1 < n) && (nums[end+1] == nums[end] + 1)){
                end++;
            }
            StringBuilder curr = new StringBuilder();
            curr.append(nums[start]);
            if(start != end){
                curr.append("->");
                curr.append(nums[end]);
            }
            start = end + 1;
            end++;
            res.add(curr.toString());
        }
        return res;
    }

    // 合并区间
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        int[] currInterval = intervals[0];
        for(int i = 1; i < n; i++){
            if(currInterval[1] >= intervals[i][0]){
                currInterval[1] = Math.max(currInterval[1], intervals[i][1]);
            }else{
                res.add(currInterval);
                currInterval = intervals[i];
            }
        }
        res.add(currInterval);

        return res.toArray(new int[res.size()][]);
    }

    // 用最少数量的箭引爆气球 (重点复习)
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[0] - o2[0]);
        int n = points.length;
        int[] currInterval = points[0];
        int arrows = 1;
        int currentEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // 如果当前气球的起始位置大于上一个箭的结束位置
            if (points[i][0] > currentEnd) {
                arrows++; // 需要发射新的箭
                currentEnd = points[i][1]; // 更新箭的结束位置
            }
        }

        return arrows;
    }

    public static boolean isValid(String s) {
        Map<Character, Character> parenteeses = new HashMap<>();
        parenteeses.put('(', ')');
        parenteeses.put('{', '}');
        parenteeses.put('[', ']');
        if(s.length() < 2)
            return false;
        int n = s.length();
        Deque<Character> stack = new LinkedList<>();
        int i = 0;
        while(i < n){
            char ch = s.charAt(i);
            if(parenteeses.containsKey(ch))
                stack.push(ch);
            else{
                if(stack.isEmpty())
                    return false;
                char pop = stack.peek();
                if(ch == parenteeses.get(pop))
                    stack.pop();
            }
            i++;
        }
        return stack.isEmpty();
    }

    // 简化路径
    public static String simplifyPath(String path) {
        List<String> directories = Stream.of(path.split("/")).filter(item -> !item.isEmpty()).collect(Collectors.toList());
        Deque<String> matches = new LinkedList<>();
        int n = directories.size();
        int i = 0;
        while(i < n){
            String curr = directories.get(i);
            if((matches.isEmpty() && curr.equals("..")) || curr. equals(".") ){
                i++;
                continue;
            }
            //    continue;
            if(!matches.isEmpty() && curr.equals(".."))
                matches.pop();
            else
                matches.push(curr);
            i++;
        }
        StringBuilder res = new StringBuilder();
        int matchSize = matches.size();
        for(int k = 0; k < matchSize; k++){
            res.append("/");
            res.append(matches.pollLast());
        }
        return matchSize != 0 ? res.toString() : "/";

    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        int n = tokens.length;
        for(int i = 0; i < n; i++){
            char curr = tokens[i].toCharArray()[0];
            if(curr <= 9 && curr >= 0)
                stack.push(Integer.valueOf(tokens[i]));
            else{
                Integer rightOperationNum = stack.pop();
                Integer leftOperationNum = stack.pop();
                Integer result = 0;
                switch(curr) {
                    case '+':
                        result = leftOperationNum + rightOperationNum;
                        break;
                    case '-':
                        result = leftOperationNum - rightOperationNum;
                        break;
                    case '*':
                        result = leftOperationNum * rightOperationNum;
                        break;
                    case '/':
                        result = leftOperationNum / rightOperationNum;
                        break;
                }
                stack.push(result);
            }
        }
        Integer.valueOf("123");
        return stack.pop();

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode p = dummy;

        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int currSum = num1 + num2 + carry;
            int currVal = currSum  % 10;
            carry = currSum / 10;
            ListNode currNode = new ListNode();
            currNode.val = currVal;
            p.next = currNode;
            p = p.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2  = l2.next;
        }
        return dummy.next;

    }

    public static void moveZeroes(int[] nums) {

        return;
    }

    // 回溯--分割回文串 (每天必看)
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    public static void backtrack(List<List<String>> res, List<String> tempList, String s, int start){
        if(start == s.length()){
            res.add(new ArrayList<>(tempList));
            return;
        }
        for(int end = start + 1; end <= s.length(); end++ ){
            String nextSubstring = s.substring(start, end);
            if(isPalindrome(nextSubstring)){
                tempList.add(nextSubstring);
                backtrack(res, tempList, s, end);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static boolean isPalindrome(String s){
        if(s.length() < 2)
            return true;
        int n = s.length();
        int left = 0, right = n - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    // 反转链表
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right)
            return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode preLeft = dummy;
        for(int i = 0; i < left - 1; i++){
            preLeft = preLeft.next;
        }
        ListNode curr = preLeft.next;
        // 当前反转节点的前驱节点
        ListNode prev = null;
        for(int i = 0; i < (right - left  + 1); i++){
            //  移动至下一个需要逆转节点，防止断链
            //if(curr == null)
            //    break;
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        preLeft.next.next = curr;
        preLeft.next = prev;

        return dummy.next;

    }

    public static ListNode reverse(ListNode head){
        ListNode dummy = new ListNode();
        ListNode p = head;
        while(head != null){
            head = head.next;
            p.next = dummy.next;
            dummy.next = p;
            p = head;
        }
        return dummy.next;
    }

    // 560. 和为 K 的子数组 前缀和+哈希表
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0,1);
        int prefixSum = 0;
        int res = 0;
        for(int num : nums){
            prefixSum += num;
            if(count.containsKey(prefixSum - k)){
                res += count.get(prefixSum-k);
            }
            count.put(prefixSum, count.getOrDefault(prefixSum,0) + 1);
        }
        return res;
    }

    // 287. 寻找重复数
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        slow = nums[0];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }

    // 75. 颜色分类
    public static void sortColors(int[] nums) {
        int n = nums.length;
        int left = 0, mid = 0, right = n-1;
        while(mid <= right){
            if(nums[mid] == 0){
                swap(nums, left, mid);
                left++;
                mid++;
            }else if(nums[mid] == 1){
                mid++;
            }else if(nums[mid] == 2){
                swap(nums, right, mid);

                right--;
            }
        }

    }
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 169. 多数元素
    public static int majorityElement(int[] nums) {
        int candidate = nums[0];
        int votes = 1;
        for(int i = 1; i < nums.length; i++){
            if(votes == 0)
                candidate = nums[i];
            if(candidate == nums[i])
                votes++;
            else
                votes--;
        }
        return candidate;
    }
    // 31. 下一个排列
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        // 从后往前遍历  找到第一个nums[i] < nums[i+1] 的数
        while((i >= 0) && nums[i] >= nums[i+1])
            i--;
        // 从i的右边即(i+1)到末尾 找到第一个比nums[i]大的数

        if(i >= 0){
            int j = n - 1;
            while(nums[j] <= nums[i])
                j--;
            swap(nums, i ,j);
        }
        // 反转(i+1)到末尾的数组
        reverseBetween(nums, i+1, n-1);

    }

    public static void reverseBetween(int[] nums, int start, int end){
        if(start == end)
            return;
        while(start < end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1)
            return "";
        int left = 0, right = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > right - left){
                right = i + len / 2;
                left = i - (len-1) / 2;
            }
        }
        return s.substring(left, right+1);
    }

    // 中心扩展算法
    public static int expandAroundCenter(String s, int left , int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return (right-1) - (left+1) + 1;
    }

    // 72. 编辑距离
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for(int i = 0; i <= n; i++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
            }
        }
        return dp[m][n];
    }

    // 1143. 最长公共子序列
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1+1][n2+1];
        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++ ){
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

            }
        }
        return dp[n1][n2];
    }

    public static void findAllSubstring(String s, int start){
        if(start == s.length()){
            return;
        }
        for(int end = start + 1; end <= s.length(); end++){
            String nextSubstring = s.substring(start, end);
            findAllSubstring(s, end);
            System.out.println(nextSubstring);
        }
    }

    // 239. 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[n-k+1];
        for(int i = 0; i < n;i++){
            if(!queue.isEmpty() && queue.peek() < i-k+1)
                queue.poll();
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i] )
                queue.pollLast();
            queue.offer(i);
            if(i >= k-1)
                res[i-k+1] = nums[queue.peek()];
        }
        return res;
    }

    // 53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int currSum = nums[0];
        for(int i = 1; i < n; i++){
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    // 238. 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftProduct = new int[n];
        leftProduct[0] = 1;
        for(int i = 1; i < n; i++)
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        int[] rightProduct = new int[n];
        rightProduct[n-1] = 1;
        for(int i = n - 2; i >= 0; i--)
            rightProduct[i] = rightProduct[i+1] * nums[i+1];
        int[] res = new int[n];
        for(int i = 0; i < n; i++)
            res[i] = leftProduct[i] * rightProduct[i];
        return res;
    }

    // 41. 缺失的第一个正数
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            // 只考虑区间范围内的数据，即1-n,对于每个数i将其放到索引为(i-1)的位置上
            // 每次会将一个数放到正确的位置上
            // 由于每次刚好将(i+1)放在了i位置上，所以需要处理
            while(nums[i] > 0 && nums[i] <= n &&  nums[nums[i] - 1] != nums[i]){
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[nums[i] - 1] = temp;
            }
        }
        for(int i = 0; i < n; i++)
            if(nums[i] != i+1)
                return i+1;
        return n+1;
    }
    //

    // 98. 验证二叉搜索树
    Long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null ||(root.left == null && root.right == null))
            return true;
        return inOrder(root);

    }
    public boolean inOrder(TreeNode root){
        if(root == null)
            return true;
        if(!(inOrder(root.left)))
            return false;
        if(pre >= root.val)
            return false;
        pre = (long)root.val;
        return inOrder(root.right);
    }

    //230. 二叉搜索树中第 K 小的元素
    int countdown = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return res;
    }

    public void inOrder(TreeNode root, int k ){
        if(root == null)
            return ;
        inOrder(root.left, k);
        countdown++;
        if(countdown == k){
            res = root.val;
            return;
        }

        inOrder(root.right, k);

    }

    // 108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {

        int n = nums.length;
        return convert(nums,0, n-1);


    }

    public static TreeNode  convert(int[] nums, int left, int right){
        if(left > right)
            return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = convert(nums, left, mid-1);
        root.right = convert(nums, mid + 1, right);
        return root;
    }

    // 101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetricTree(root.left, root.right);
    }

    public static boolean isSymmetricTree(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;
        if(left.val != right.val)
            return false;
        return isSymmetricTree(left.left, right.right) && isSymmetricTree(left.right, right.left);
    }


    // 226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        if(root.left == null && root.right == null)
            return root;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }

    // 102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> currList = new ArrayList<>();
            int size = queue.size();
            while(size != 0){
                TreeNode curr = queue.poll();
                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);
                currList.add(curr.val);
                size--;
            }
            res.add(currList);
        }
        return res;
    }

    // 199. 二叉树的右视图(利用层序遍历)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = queue.poll();
                if(i == size - 1)
                    res.add(curr.val);
                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);
            }
        }
        return res;
    }

    // 114. 二叉树展开为链表
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        flatten(root.left);
        flatten(root.right);
        if(root.left != null){
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;
            TreeNode curr = root.right;
            while(curr.right != null)
                curr = curr.right;
            curr.right = temp;
        }

    }

    //124. 二叉树中的最大路径和\
    int maxPathSum = Integer.MIN_VALUE;
    public int helper(TreeNode root){
        if(root == null)
            return 0;
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);
        int currPathSum = root.val + left + right;
        maxPathSum = Math.max(currPathSum, maxPathSum);
        return root.val + Math.max(left, right);

    }

    // 105. 从前序与中序遍历序列构造二叉树
    private Map<Integer, Integer> inorderIndexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 构造哈希表以快速定位中序遍历中的根节点索引
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        // 从0开始的preorder索引和inorder的范围构建树
        return buildSubTree(preorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildSubTree(int[] preorder, int preorderIndex, int inorderLeft, int inorderRight) {
        if (inorderLeft > inorderRight) {
            return null;
        }

        // 获取根节点值和对应的TreeNode
        int rootValue = preorder[preorderIndex];
        TreeNode root = new TreeNode(rootValue);

        // 获取根节点在inorder中的位置
        int inorderIndex = inorderIndexMap.get(rootValue);

        // 计算左子树节点的数量
        int leftSubtreeSize = inorderIndex - inorderLeft;

        // 递归构造左子树和右子树
        root.left = buildSubTree(preorder, preorderIndex + 1, inorderLeft, inorderIndex - 1);
        root.right = buildSubTree(preorder, preorderIndex + leftSubtreeSize + 1, inorderIndex + 1, inorderRight);

        return root;
    }

    // 215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        // 桶排序嘿嘿
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num : nums){
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        int[] count = new int[max- min + 1];
        for(int num : nums)
            count[num - min]++;
        for(int i = count.length - 1; i >= 0; i--){
            k -= count[i];
            if(k <= 0)
                return i + min;
        }
        return 10001;
    }

    // 回溯输出字典字符串(只有ab两个字符)
    public static String findKthString(int n, int k) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), n, k);
        return result.get(k - 1);
    }

    private static boolean backtrack(List<String> result, StringBuilder path, int n, int k) {
        if (path.length() == n) {
            System.out.println(path);
            result.add(path.toString());
            return result.size() == k; // 找到第 k 个时返回 true 以停止递归
        }

        for (char c : new char[]{'a', 'b'}) {
            path.append(c);
            if (backtrack(result, path, n, k)) return true; // 如果找到第 k 个，提前返回
            path.deleteCharAt(path.length() - 1); // 回溯
        }
        return false;
    }

    public String longestWord(List<String> words) {
        // 排序：首先按长度降序排列，如果长度相同按字典序升序
        Collections.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());

        // 用一个 HashSet 存储单词，方便 O(1) 查找
        Set<String> wordSet = new HashSet<>();
        // 遍历单词列表，逐个检查
        for (String word : words) {
            // 如果 word 的所有前缀都在 wordSet 中，说明 word 可以被构成
            if (word.length() == 1 || wordSet.contains(word.substring(0, word.length() - 1))) {
                return word;  // 返回当前的单词
            }
            wordSet.add(word);  // 将当前的单词加入 set 中
        }

        // 如果没有符合的返回空
        return "";
    }


    public static int minPlayCount(int[] cards) {
        // 统计每种牌的数量
        Map<Integer, Integer> cardCount = new HashMap<>();
        for (int card : cards) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }

        List<Integer> cardTypes = new ArrayList<>(cardCount.keySet());
        int n = cardTypes.size();

        // 动态规划数组，2^n 表示所有可能的出牌组合
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;  // 空手牌的出牌次数为0

        // 遍历所有状态
        for (int state = 0; state < (1 << n); state++) {
            if (dp[state] == Integer.MAX_VALUE) continue;

            // 逐一查看每张牌的状态
            for (int i = 0; i < n; i++) {
                int card = cardTypes.get(i);
                int num = cardCount.get(card);

                // 若当前状态不包含该牌，则继续考虑以下出牌方案
                if ((state & (1 << i)) == 0) {
                    // 标记当前状态已经包含该牌
                    int newState = state | (1 << i);

                    // 四张相同的牌
                    if (num >= 4) dp[newState] = Math.min(dp[newState], dp[state] + 1);

                    // 三张相同的牌
                    if (num >= 3) dp[newState] = Math.min(dp[newState], dp[state] + 1);

                    // 对子
                    if (num >= 2) dp[newState] = Math.min(dp[newState], dp[state] + 1);

                    // 单张
                    dp[newState] = Math.min(dp[newState], dp[state] + 1);
                }
            }
        }

        // dp[(1 << n) - 1] 存储了所有手牌出完的最小次数
        return dp[(1 << n) - 1];
    }

    public static String longestWord(String[] words) {
        Arrays.sort(words, (o1, o2) -> o1.length() == o2.length() ? o2.compareTo(o1) : o2.length() - o1.length());
        Set<String> wordSet  = new HashSet<>(Arrays.asList(words));
        for(String word : words){
            wordSet.remove(word);
            if(canFrom(word, wordSet))
                return word;
        }

        return "";

    }

    // 判断当前单词是否能由其他单词组成
    private static boolean canFrom(String word, Set<String> wordSet) {
        if(word.length() == 0)
            return true;
        for(int i = 1; i < word.length(); i++){
            if(wordSet.contains(word.substring(0, i)) && wordSet.contains(word.substring(i)))
                return true;
        }

        return false;
    }

    public void backtrack(List<List<Integer>> res, List<Boolean> visited, List<Integer> curr, int[] nums){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr));
            return;
        }
        StringBuilder r = new StringBuilder();
        r.deleteCharAt(r.length() - 1);
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n -1;
        int start = 0, end = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return n % 2 == 0 ? new int[]{mid, mid + 1} : new int[]{mid-1, mid};
            }else if( nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return new int[]{-1, -1};
    }

    // 33. 搜索旋转排序数组
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else
                    right = mid - 1;
            }

        }
        return -1;
    }

    // 153. 寻找旋转排序数组中的最小值
    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if((mid == 0) && nums[mid] < nums[mid] +1)
                return mid;
            if((mid == n-1) && nums[mid] < nums[mid-1])
                return mid;
            if(nums[mid] < nums[mid] - 1 && nums[mid] < nums[mid] + 1)
                return mid;
            if(nums[left] <= nums[left]){
                right = mid + 1;
            }else{
                left = mid - 1;
            }
        }
        return -1;
    }

    // 394. 字符串解码
    public String decodeString(String s) {
        Deque<Integer> countStack = new LinkedList<>(); // 用于存储重复次数
        Deque<StringBuilder> stringStack = new LinkedList<>(); // 用于存储字符串
        StringBuilder currentString = new StringBuilder(); // 当前构造的字符串
        int k = 0; // 当前的重复次数
        // a1[bc]d
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 构建重复次数
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 遇到 '['，将当前的 k 和 currentString 入栈
                countStack.push(k);
                stringStack.push(currentString);
                // 重置 k 和 currentString
                k = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                // 遇到 ']'，计算解码结果
                int repeatTimes = countStack.pop();
                StringBuilder decodedString = stringStack.pop();
                // 将当前字符串重复 repeatTimes 次并追加到之前的字符串
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // 普通字符追加到当前字符串
                currentString.append(c);
            }
        }

        return currentString.toString();
    }

    // 739. 每日温度
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[2];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && temperatures[i] > stack.peek()){
                int preIndex = stack.pop();
                res[preIndex] = i - preIndex;
            }
            stack.push(temperatures[i]);
        }
        return res;
    }

    // 994. 腐烂的橘子
    public static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2)
                    queue.offer(new int[]{i, j});
                else if(grid[i][j] == 0)
                    grid[i][j] = 2;
            }
        }
        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int minutes = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] curr = queue.poll();
                for(int[] direction : directions){
                    int nextRow = curr[0] + direction[0];
                    int nextCol = curr[1] + direction[1];
                    if(nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 1){
                        grid[nextRow][nextCol] = 2;
                        queue.offer(new int[]{nextRow,nextCol});
                    }
                }
            }
            if(!queue.isEmpty())
                minutes++;
        }
        return queue.isEmpty() ? minutes : -1;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        for(int i = 1; i <= numRows; i++){
            List<Integer> curr = new ArrayList<>(i);
            // curr.add(1);
            // curr.add(i - 1, 1);

            // for(int j = 0; j < pre.size() - 1; j++){
            //     int num = pre.get(j) + pre.get(j+1);
            //     curr.add(num);
            // }
            curr.add(1);
            for(int j = 0; j < pre.size() - 1; j++){
                int num = pre.get(j) + pre.get(j+1);
                curr.add(num);
            }
            if(i > 1)
                curr.add(1);
            res.add(curr);
            pre = curr;
        }
        return res;
    }

    // 279. 完全平方数
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            int boundary = (int) Math.sqrt(i);
            dp[i] = Integer.MAX_VALUE;
            for(int j = 1; j <= boundary; j++){
                dp[i] = Math.min(dp[i-j*j] + 1, dp[i]);
            }
        }
        return dp[n];

    }

    // 763. 划分字母区间
    public static List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] lastIndex = new int[26];
        for(int i = 0; i < n; i++)
            lastIndex[s.charAt(i) - 'a'] = i;
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for(int i = 0; i < n; i++){
            char curr = s.charAt(i);
            end = lastIndex[curr - 'a'];
            if(i == end){
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }

    // 25. K 个一组翻转链表
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 0)
            return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode  preStart = dummy,  end = dummy;
        int step = 0;
        while(true){
            while(step != k && end != null){
                end = end.next;
                step++;
            }
            if(end == null){
                break;
            }
            ListNode start = preStart.next;
            ListNode next = end.next;
            end.next = null;
            preStart.next = reverseList(start);

            start.next = next;
            preStart = start;
            end = start;
            step = 0;
        }
        return dummy.next;

    }

    // 翻转给定区间链表并返回头尾节点
    public static ListNode reverseList(ListNode start){
        ListNode pre = null;
        ListNode curr = start;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    // 快速排序
    public static void quickSort(int[] nums, int left, int right){
        int pivot = nums[left];
        int i = left, j = right;
        // int[] nums = {5, 6, 7, 4, 2};
        while(i < j){
            while((i < j) && nums[j] > pivot)
                j--;
            while((i < j) && nums[i] < pivot)
                i++;
            if((i < j) && nums[i] == nums[j]){
                i++;
            }else{
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }

        }
        if(i - 1 > left)
            quickSort(nums, left, i -1);
        if(j + 1 < right)
            quickSort(nums, j + 1, right);
    }

    // 快速选择算法求第k的数据(基于快排)
    public static int quickSelect(int[] nums, int k){
        int n = nums.length;
        int targetIndex = n - k;
        int left = 0, right = n - 1;
        while(left <= right){
            int pivotIndex = partition(nums, left, right);
            if(pivotIndex == targetIndex){
                return nums[pivotIndex];
            }else if(pivotIndex < targetIndex){
                left = pivotIndex + 1;
            }else{
                right = pivotIndex - 1;
            }
        }
        return - 1;
    }

    public static int partition(int[] nums, int left, int right){
        int pivot = nums[left];
        int i = left, j = right;
        while(i < j){
            while((i < j) && nums[j] > pivot)
                j--;
            while((i < j) && nums[i] < pivot)
                i++;
            if((i < j) && nums[i] == nums[j]){
                i++;
            }else{
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }

        }
        return i;
    }

    // 452. 用最少数量的箭引爆气球
    public static int findMinArrowShotss(int[][] points) {
        if(points == null || points.length == 0)
            return 0;
        // 右边界排序
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[1] < o2[1])
                    return -1;
                else if(o1[1] > o2[1])
                    return 1;
                else
                    return 0;
            }
        });
        int arrows = 1;
        int currentEnd = points[0][1];
        for(int i = 1; i < points.length; i++){
            if(points[i][0] > currentEnd){
                arrows++;
                currentEnd = points[i][1];
            }
        }
        return arrows;
    }


    // 警察抓小偷
    public static int minTimeToCatchThief(int policePosition, int thiefPosition) {
        // 边界情况处理
        if (policePosition == thiefPosition) {
            return 0; // 警察与小偷位置相同，不需要移动
        }
        int[] directions = {1, -1, 2};
        // 创建队列用于BFS
        Queue<int[]> queue = new LinkedList<>();
        // visited数组用于记录访问状态，避免重复访问
        boolean[] visited = new boolean[100001];

        // 初始状态加入队列（位置，时间）
        queue.add(new int[]{policePosition, 0});
        visited[policePosition] = true;

        // BFS搜索
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int time = current[1];
            if (position == thiefPosition) {
                return time; // 找到小偷，返回时间
            }
            // 遍历所有可能的移动方向
            for (int direction : directions) {
                // 检查是否越界或已经访问过
                int nextPosition = direction == 2 ? position * 2 : position + direction;
                if (nextPosition >= 0 && nextPosition <= 100000 && !visited[nextPosition]) {
                    queue.add(new int[]{nextPosition, time + 1});
                    visited[nextPosition] = true;
                }
            }
        }

        return -1; // 如果无法到达小偷，返回-1
    }

    public static int firstZeroOneStringPosition(String s){
        int n = s.length();
        int left = 0, right = n -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(s.charAt(mid) == '0' && s.charAt(mid+1) == '1')
                return mid;
            if(s.charAt(mid) == '0')
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // 212. 单词搜索 II
    static int[][] directions = {{-1,0}, {0, -1}, {1, 0}, {0,1}};
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if(board == null || board.length == 0 || words == null || words.length == 0)
            return res;
//        Set<String> foundWords = new HashSet<>();
//        for(String word : words){
//
//            for(int i = 0; i < board.length; i++){
//                if(foundWords.contains(word))
//                    break;
//                for(int j = 0; j < board[0].length; j++){
//                    if(search(board, word, 0, i, j)){
//                        res.add(word);
//                        foundWords.add(word);
//                        break;
//                    }
//
//                }
//            }
//        }
        // 前缀树优化
        root = new TrieNode();
        for(String word : words)
            insert(word);
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(root.children.containsKey(board[i][j]))
                    search(board, root, i, j, res);
            }
        }
        return res;
    }

    public static   boolean search(char[][] board, String word, int index, int row, int col){
        if(index == word.length())
            return true;
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index))
            return false;
        char temp = board[row][col];
        board[row][col] = '#';
        for(int[] direction : directions){
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            if(search(board, word, index + 1, nextRow, nextCol))
                return true;
        }
        board[row][col] = temp;
        return false;
    }

    // 前缀树优化
    // 定义前缀树
    private static class TrieNode{
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    private static TrieNode root;
    public static void insert(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            // if(curr.children.get(ch) == null)
            //     curr.children.get(ch) = new TrieNode();
            curr.children.putIfAbsent(ch, new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.word = word;
    }

    public static void search(char[][] board, TrieNode node, int row, int col, List<String> res){
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return;
        char ch = board[row][col];
        if(ch == '#' || !node.children.containsKey(ch))
            return;

        board[row][col] = '#';
        TrieNode curr = node.children.get(ch);
        if(curr.word != null){
            res.add(curr.word);
            curr.word = null;
        }


        for(int[] direction : directions){
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            search(board,  curr, nextRow, nextCol, res);
        }
        board[row][col] = ch;
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>();
        for(int i = 0; i < nums1.length; i++){

            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }

        List<List<Integer>> res = new ArrayList<>();
        while(k-- > 0 && !minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            int index = curr[2];
            res.add(Arrays.asList(curr[0], curr[1]));
            if(index + 1 < nums2.length){
                minHeap.offer(new int[]{curr[0], nums2[index+1], index + 1});
            }
        }

        return res;

    }

    public static List<Integer> findSubstring(String s, String[] words) {
        if(s.length() < words.length * words[0].length())
            return null;
        Set<String> concatenatedSubstrings = new HashSet<>();
        backtrack(words, concatenatedSubstrings, new StringBuilder(), new HashSet<>());
        List<Integer> res = new ArrayList<>();
        int step = words[0].length() * words.length;
        for(int i = 0; i <= s.length() - step; i++){
            if(concatenatedSubstrings.contains(s.substring(i, i + step)))
                res.add(i);
        }
        return res;

    }



    public static void backtrack(String[] words, Set<String> res, StringBuilder curr, Set<Integer> visited){
        int len = words.length * words[0].length();
        if(curr.length() == len){
            res.add(curr.toString());
            return;
        }
        for(int i = 0; i < words.length; i++){
            if(visited.contains(i))
                continue;
            visited.add(i);
            curr.append(words[i]);
            backtrack(words, res, curr, visited);
            curr.delete(len - words[0].length(), len);
            visited.remove(i);
        }
    }

    static int count = 0;
    public static int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        backtrack(nums, k, new ArrayList<>(), 0);
        return count;
    }

    public static void backtrack(int[] nums, int k, List<Integer> curr, int index){
        if(index == nums.length || nums[index] - k <= 0)
            return;
        for(int i = index; i < nums.length; i++){
            curr.add(nums[i]);
            count++;
            backtrack(nums, k, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
    private static final  char[] VOWELLETTERS = {'a', 'e', 'i', 'o', 'u'};

    public static int largestVariance(String s) {
        int n = s.length();
        int res = 0;
        for(int i = 0; i < n; i++){
            int[] count = new int[26];
            int countMin = Integer.MAX_VALUE, countMax = Integer.MIN_VALUE;
            for(int j = i; j < n; j++){
                char ch = s.charAt(j);
                count[ch - 'a']++;
                countMin = Math.min(countMin, count[ch - 'a']);
                countMax = Math.max(countMax, count[ch - 'a']);
                if(countMax == j - i + 1)
                    countMin = countMax;
                res = Math.max(countMax - countMin, res);
            }



        }
        return res;
    }

    public static List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        while(count < nums.length){
            List<Integer> curr = new ArrayList<>();
            for(int i = 0; i < nums.length; i++){
                if(visited.contains(nums[i]) || nums[i] == -1)
                    continue;
                curr.add(nums[i]);
                count++;
                visited.add(nums[i]);
                nums[i] = -1;
            }
            res.add(curr);
            visited.clear();

        }
        return res;
    }

    // 152. 乘积最大子数组
    

    // 回溯输出字典字符串  重排链表 手写FIFO 用数组写 括号匹配（lc678），正反两次遍历搞定  手撕最小距离和
    // 深挖项目

    public static void main(String[] args) {
        // int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        // int[][] matrix = {{1, 3}, {8, 10}, {2, 6}};
        // int target = 213;
        //String s = "qrsvbspk";
        // threeSum(nums);
        // minSubArrayLen(target, nums);
        // lengthOfLongestSubstring(s);
        // customizeSort();
        // spiralOrder(matrix);

        // int[] arr = Arrays.copyOf(nums, 12);
        // System.out.println(Arrays.toString(arr));
        //String s1 = "nl";
        // String s2 = "cx";
        // isAnagram(s1, s2);
        // int[] nums = {0,1,2,4,5,7};
//        Arrays.sort(matrix, (o1, o2) -> o1[0] - o2[0]);
//        for (int[] ints : matrix) {
//            System.out.println(Arrays.toString(ints));
//        }
//        List<List<Integer>> res = new ArrayList<>();
//        res.toArray(new int[2])

        // summaryRanges(nums);
        // int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        // findMinArrowShots(points);
//        String s = "(])";
//        isValid(s);
//        String s1 = "/home//foo/";
//        List<String> s2 = Stream.of(s1.split("/")).filter(item -> !item.isEmpty()).collect(Collectors.toList());
//        Deque<String> matches = new LinkedList<>();
        // String s = "/../";
        // simplifyPath(s);
        // System.out.println(simplifyPath(s));
        // String[] tokens = {"2","1","+","3","*"};
        // evalRPN(tokens);
        // int[] data1 = {2,4,3};
        // int[] data2 = {5,6,4};
        // ListNode l1 = ListNode.createLinkedList(data1);
        // ListNode l2 = ListNode.createLinkedList(data2);
        // addTwoNumbers(l1, l2);
        // int[] nums = {4,2,4,0,0,3,0,5,1,0};
        // moveZeroes(nums);

//        Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
//        Deque<Integer> deque = new LinkedList<>();
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add()
//        int[] data = {1,2,3,4,5};
//        ListNode head =  ListNode.createLinkedList(data);
//        reverseBetween(head, 2, 4);
//        String s = "bac";
//        char[] chars = s.toCharArray();
//        Arrays.sort(chars);
//        System.out.println(Arrays.toString(chars));
        // String s = "abc";
        // findAllSubstring(s, 0);
        // int[] nums = {3,4,-1,1};
        // int[] cards = {1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        // System.out.println("最少出牌次数: " + minPlayCount(cards));
//        Scanner scanner = new Scanner(System.in);
//        // 单行输入
//        String input = scanner.nextLine();
//        // 多行输入
//        int n = Integer.parseInt(scanner.nextLine());
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++)
//            arr[i] = Integer.parseInt(scanner.nextLine());
//        Map<Integer, Integer> map = new HashMap<>();
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid);
//
//        String[] words = {"qlmql","qlmqlmqqlqmqqlq","mqqlqmqqlqmqqlq","mqqlq","mqqlqlmlsmqq","qmlmmmmsm","lmlsmqq","slmsqq","mslqssl","mqqlqmqqlq"};
//        longestWord(words);
//
//        int[] nums = {3,4,5,1,2};
//        findMin(nums);
//        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
//        orangesRotting(grid);
//
//        String[] words = {"qlmql","qlmqlmqqlqmqqlq","mqqlqmqqlqmqqlq","mqqlq","mqqlqlmlsmqq","qmlmmmmsm","lmlsmqq","slmsqq","mslqssl","mqqlqmqqlq"};
//        longestWord(words);

//        int a = (int) Math.sqrt(12);
//        List<Integer> res = new ArrayList<>(1);
//        res.add(1);
//        res.add(0, 1);
//        System.out.println(res.size());
//        numSquares(12);
//        String s = "ababcbacadefegdehijhklij";
//        partitionLabels(s);
//        Map<Integer, Integer> cache = new HashMap<>();
//        cache.size();
//        int[] data = {1,2,3,4,5};
//        ListNode head =  ListNode.createLinkedList(data);
//        reverseKGroup(head, 2);
//        int[] nums = {5, 6, 7, 4, 5};
//        int res = quickSelect(nums, 2);
//        // System.out.println(res);
//        int[][] points = {{10,16}, {2,8}, {1, 6}, {7, 12}};
//        findMinArrowShotss(points);
//        Arrays.sort(points, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[1] < o2[1])
//                        return 1;
//                else if(o1[1] > o2[1])
//                        return -1;
//                else
//                    return 0;
//            }
//        });
//        minTimeToCatchThief(1, 10);
//        // System.out.println(minTimeToCatchThief(1, 10));
//        Deque<TreeNode> queue = new LinkedList<>();
//        Queue<TreeNode> stack = new LinkedList<>();
//        List<Integer> currList = new ArrayList<>();
//        List<String> wordDict = new ArrayList<>();
//        Set<String> set = new HashSet<>();
//        Collections.sort(wordDict, ((o1, o2) -> o2.length() - o1.length()));
//        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] words = {"oath","pea","eat","rain"};
//        findWords(board, words);
//         int[] nums1 = {1, 7, 11};
//         int[] nums2 = {2, 4, 6};
//         int k = 3;
//         kSmallestPairs(nums1, nums2, k);
//        String s = "barfoothefoobarman";
//        String[] words = {"foo","bar"};
//        findSubstring(s, words);
//        List<Integer> intervals = new ArrayList<>();
//        intervals.toArray();
//        String s = "ababab";
//        largestVariance(s);
//        int[] nums = {1, 2, 3};
//        int[] nums = {1,3,4,1,2,3,1};
//        findMatrix(nums);
//
//
//        String s = "abc";
//        s.indexOf("a", 0);
//        s.compareTo("acd");
//        int[] edges = {3,3,4,2,3};
//        longestCycle(edges);



    }

}
