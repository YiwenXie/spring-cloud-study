package com.atyiwen.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ywxie
 * @date 2021/8/12 22:20
 * @describe
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 得到第几次访问次数
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0: current + 1;
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("******第几次访问，次数next:" + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        // 得到服务下标
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
