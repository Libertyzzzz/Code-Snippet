package com.xu.code.practice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {

    /**
     * @Author liberty
     * @Date 2024/10/14 16:29
     */
    private Object proxyObject;

    public DynamicProxyHandler(Object proxyObject){
        this.proxyObject = proxyObject;
    }

    // 方法增强
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理类方法增强");
        Object res = method.invoke(proxyObject, args);
        System.out.println("动态代理类方法增强");
        return res;
    }
}
