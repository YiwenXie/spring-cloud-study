package com.atyiwen.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ywxie
 * @date 2021/8/7 14:36
 * @describe 相当于applicationContext.xml，里面配置各种bean
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * RestTemplate配置类：提供多种便捷访问HTTP服务的方法。spring客户端模板工具类，restful服务模板类
     * @LoadBalanced: 负载均衡注解
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
