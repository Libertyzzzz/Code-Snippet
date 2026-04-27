package com.xu.code.practice.proxy;

public class JVMDemo {

    /**
     * @Author liberty
     * @Date 2024/11/1 14:39
     */
    public static void gcTest(){
        byte[] allocation1 = new byte[1024*1024];
    }

    public static void main(String[] args) {
//        long loop = 10000000000000l;
//        while(loop-- != 0){
//            System.out.println(loop);
//        }
        gcTest();
    }
}
