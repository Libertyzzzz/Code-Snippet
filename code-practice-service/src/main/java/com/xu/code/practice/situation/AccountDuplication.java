package com.xu.code.practice.situation;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class AccountDuplication {
    static List<Integer> accountIds = new ArrayList<>();

    /**
     * @Author liberty
     * @Date 2024/11/6 02:52
     */

    /**
     * 场景题，数据大于内存，去重排序等
     */

    public static void main(String[] args) {
        // 假设 40 亿个账号，设定 false positive rate 为 0.1%
        int expectedAccountCount = 400000000; // 40亿
        double falsePositiveRate = 0.001;
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), expectedAccountCount, falsePositiveRate);

        // 模拟去重操作
        for (int accountId : accountIds) {
            if (!filter.mightContain(accountId)) {
                // 新账号，添加到结果集
                filter.put(accountId);
                // 将账号写入结果，进行去重
            } else {
                // 账号重复，跳过
            }
        }
    }
}
