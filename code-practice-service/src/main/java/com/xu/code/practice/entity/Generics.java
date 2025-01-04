package com.xu.code.practice.entity;

import java.util.List;

public class Generics {

    /**
     * @Author liberty
     * @Date 2025/1/2 14:57
     */

    public static <T> void print(T value){
        System.out.println(value.toString());
    }

    public static void main(String[] args) {
        print("hello");
        print(1234);
    }

    public void read(List< ? extends Number> list){
        for(Number num : list)
            System.out.println(num);


    }

    public void write(List<? super Number> list){
        list.add(new Integer(2));
        list.add(12.34);

    }




}
