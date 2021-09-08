package com.atyiwen.springcloud.controller;

import com.atyiwen.springcloud.entities.CommonResult;
import com.atyiwen.springcloud.entities.Payment;
import com.atyiwen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

}
