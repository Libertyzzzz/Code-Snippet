package com.xu.code.practice.jvm;

import java.util.List;

public class GenericTypes {

    /**
     * @Author liberty
     * @Date 2025/2/20 17:08
     */
    public static void main(String[] args) {

    }

    // 这里会报错，因为存在泛型擦除，那么这两个方法的特征将会变得一模一样，因此无法重载
    // public static void method(List<Integer> list){

    // }

    public static void method(List<String> list){

    }



}
