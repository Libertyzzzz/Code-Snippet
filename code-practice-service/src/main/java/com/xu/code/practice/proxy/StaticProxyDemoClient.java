package com.xu.code.practice.proxy;

import com.xu.code.practice.service.impl.ProxyExecutionAction;
import com.xu.code.practice.service.impl.RealExecutionAction;
import org.springframework.stereotype.Component;

@Component
public class StaticProxyDemoClient {

    /**
     * @Author liberty
     * @Date 2024/10/14 16:07
     */
    public static void main(String[] args) {
        RealExecutionAction realExecutionAction = new RealExecutionAction();
        ProxyExecutionAction proxyExecutionAction = new ProxyExecutionAction(realExecutionAction);
        proxyExecutionAction.performAction();
    }

}
