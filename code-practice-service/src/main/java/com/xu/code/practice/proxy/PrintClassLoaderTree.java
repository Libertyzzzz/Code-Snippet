package com.xu.code.practice.proxy;

public class PrintClassLoaderTree {

    /**
     * @Author liberty
     * @Date 2024/11/1 12:14
     */

    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        StringBuilder split = new StringBuilder("|--");
        boolean needContinue = true;
        while(needContinue){
            System.out.println(split.toString() + classLoader);
            if(classLoader == null)
                needContinue = false;
            else {
                classLoader = classLoader.getParent();
                split.insert(0,"\t");
            }
        }

    }
}
