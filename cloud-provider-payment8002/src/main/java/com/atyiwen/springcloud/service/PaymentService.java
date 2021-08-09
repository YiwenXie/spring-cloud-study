package com.atyiwen.springcloud.service;

import com.atyiwen.springcloud.entities.Payment;

/**
 * @author ywxie
 * @date 2021/8/6 23:54
 * @describe
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
