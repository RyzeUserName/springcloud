package com.lft.loadbalance;

import com.lft.loadbalance.loadBalanceConfig.AvoidScan;
import com.lft.loadbalance.loadBalanceConfig.TestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication

@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {AvoidScan.class})})
@RibbonClient(name = "client", configuration = TestConfig.class)
//@RibbonClients(value = {
//		@RibbonClient(name = "client-a", configuration = TestConfiguration.class),
//		@RibbonClient(name = "client-b", configuration = TestConfiguration.class)
//})

public class LoadbalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadbalanceApplication.class, args);
    }

//    /**
//     * 全局配置 在这可以引入其他策略
//     * @return
//     */
//    @Bean
//    public IRule iRule() {
////        //1轮询
////        new RoundRobinRule();
////        //2重试
////        new RetryRule();
////        //3最低并发
////        new BestAvailableRule();
////        //4可用过滤
////        new AvailabilityFilteringRule();
////        //5响应时间加权
////        new WeightedResponseTimeRule();
////        //6区域权衡策略
////        new ZoneAvoidanceRule();
////        //7随机
//        return new RandomRule();
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
