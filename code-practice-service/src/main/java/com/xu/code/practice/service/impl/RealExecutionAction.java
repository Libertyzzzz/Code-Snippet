package com.xu.code.practice.service.impl;

import com.xu.code.practice.service.IExecutionAction;
import org.springframework.stereotype.Service;

@Service(value = "realExecutionAction")
public class RealExecutionAction implements IExecutionAction {

    /**
     * @Author liberty
     * @Date 2024/10/14 16:13
     */

    @Override
    public void performAction() {
        System.out.println("真实动作执行");
    }
}
