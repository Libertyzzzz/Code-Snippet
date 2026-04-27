package com.xu.code.practice.service.impl;

import com.xu.code.practice.service.IExecutionAction;
import org.springframework.stereotype.Service;

@Service(value = "proxyExecutionAction")
public class ProxyExecutionAction implements IExecutionAction {
    /**
     * @Author liberty
     * @Date 2024/10/14 16:10
     */
    private RealExecutionAction realExecutionAction;

    public ProxyExecutionAction(RealExecutionAction realExecutionAction){
        this.realExecutionAction = realExecutionAction;
    }
    @Override
    public void performAction() {
        System.out.println("代理类执行方法");
        realExecutionAction.performAction();
        System.out.println("代理类执行方法");
    }
}
