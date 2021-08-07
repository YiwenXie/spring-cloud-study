package com.atyiwen.springcloud.service.impl;

import com.atyiwen.springcloud.dao.PaymentDao;
import com.atyiwen.springcloud.entities.Payment;
import com.atyiwen.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ywxie
 * @date 2021/8/6 23:55
 * @describe
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
