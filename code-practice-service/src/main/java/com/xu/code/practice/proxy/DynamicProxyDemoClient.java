package com.xu.code.practice.proxy;

import com.xu.code.practice.service.IExecutionAction;
import com.xu.code.practice.service.impl.ProxyExecutionAction;
import com.xu.code.practice.service.impl.RealExecutionAction;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class DynamicProxyDemoClient {

    /**
     * @Author liberty
     * @Date 2024/10/14 16:07
     */
    public static void main(String[] args) {
        RealExecutionAction realExecutionAction = new RealExecutionAction();
        IExecutionAction proxyInstance = (IExecutionAction) Proxy.newProxyInstance(
                realExecutionAction.getClass().getClassLoader(),
                realExecutionAction.getClass().getInterfaces(),
                new DynamicProxyHandler(realExecutionAction)
        );
        proxyInstance.performAction();
    }

}
