package com.xu.code.practice.algorithm;



import com.xu.code.practice.entity.ListNode;
import com.xu.code.practice.entity.TreeNode;

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

//        Queue<Integer> heap = new PriorityQueue<>();
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
        int[] nums = {3,4,-1,1};
        firstMissingPositive(nums);
        Deque<Integer> queue = new LinkedList<>();
        queue.size();
        List<Integer> res = new ArrayList<>();

     }

}
