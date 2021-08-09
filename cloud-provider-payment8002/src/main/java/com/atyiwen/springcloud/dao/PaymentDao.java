package com.atyiwen.springcloud.dao;

import com.atyiwen.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ywxie
 * @date 2021/8/6 23:41
 * @describe
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
