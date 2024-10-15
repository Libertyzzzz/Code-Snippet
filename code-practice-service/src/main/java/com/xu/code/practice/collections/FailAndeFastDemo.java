package com.xu.code.practice.collections;

import java.util.ArrayList;
import java.util.List;

public class FailAndeFastDemo {

    /**
     * @Author liberty
     * @Date 2024/10/15 17:02
     */

    /**
     * fail-fast : Java中集合的一种错误检测机制
     * 增强for循环使用 add/remove操作，没有使用迭代器去实现，所以会出发该机制
     * https://www.cnblogs.com/54chensongxia/p/12470446.html
     */
    public static void showFailAndFast(){
        List<String> userNames = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        for (String userName : userNames) {
            if (userName.equals("Hollis")) {
                // 此时抛出异常 ConcurrentModificationException
                // userNames.remove(userName);
                // 此时抛出异常 ConcurrentModificationException
                userNames.add("garry");
            }
        }

        System.out.println(userNames);
    }
    public static void main(String[] args) {
        showFailAndFast();
        new Thread();
    }

}
