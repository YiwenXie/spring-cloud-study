package com.atyiwen.springcloud.controller;

import com.atyiwen.springcloud.entities.CommonResult;
import com.atyiwen.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ywxie
 * @date 2021/8/7 14:29
 * @describe 客户端消费者
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001"; // 单机版写死了
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; //集群版，对外暴露不再是端口和地址，而是微服务名称

    @Resource// 注入
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
