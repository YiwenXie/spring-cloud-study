package com.atyiwen.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author ywxie
 * @date 2021/8/12 22:18
 * @describe
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
