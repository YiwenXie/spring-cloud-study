package com.atyiwen.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ywxie
 * @date 2021/8/12 21:52
 * @describe Ribbon规则类(新建在此包是因为不能被@ComponentScan 给扫到，而@SpringBootApplication内就包含此注解，所以要分开)
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();// 随机，默认是轮询
    }

}
