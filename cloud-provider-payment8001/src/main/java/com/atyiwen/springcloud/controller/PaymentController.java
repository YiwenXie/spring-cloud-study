package com.atyiwen.springcloud.controller;

import com.atyiwen.springcloud.entities.CommonResult;
import com.atyiwen.springcloud.entities.Payment;
import com.atyiwen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ywxie
 * @date 2021/8/6 23:58
 * @describe 微服务端提供者
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("********插入结果" + result);
        if (result > 0){
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        }else {
            return new CommonResult(444, "插入数据库失败", result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("********查询结果" + payment);
        if (payment != null){
            return new CommonResult(200, "查询数据库成功,serverPort:" + serverPort, payment);
        }else {
            return new CommonResult(444, "查询数据库失败", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        // 服务清单列表
        List<String> services = discoveryClient.getServices();
        for (String element: services){
            log.info("********element:" + element);
        }
        // 一个微服务下面的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances){
            log.info("instanceId:" + instance.getInstanceId() + "\t Host:" + instance.getHost() + "\t Port:" + instance.getPort()
                    + "\t uri:" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
