package com.xu.code.practice.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {

    /**
     * @Author liberty
     * @Date 2024/10/14 16:32
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> realExecutionClass = Class.forName("com.xu.code.practice.service.impl.RealExecutionAction");
        Object realExecutionInstance =  realExecutionClass.getDeclaredConstructor().newInstance();
        Method method = realExecutionClass.getMethod("performAction");
        method.invoke(realExecutionInstance);

    }

}
