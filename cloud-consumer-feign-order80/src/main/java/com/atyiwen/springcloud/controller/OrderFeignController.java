package com.atyiwen.springcloud.controller;

import com.atyiwen.springcloud.entities.CommonResult;
import com.atyiwen.springcloud.entities.Payment;
import com.atyiwen.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ywxie
 * @date 2021/9/8 22:11
 * @describe
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon，客户端默认等等一秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
