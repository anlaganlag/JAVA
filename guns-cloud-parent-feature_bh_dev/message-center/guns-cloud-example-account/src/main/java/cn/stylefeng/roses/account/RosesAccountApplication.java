package cn.stylefeng.roses.account;

import cn.stylefeng.roses.account.core.OrderSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 用户账户服务
 *
 * @author stylefeng
 * @Date 2018/1/22 21:27
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "cn.stylefeng.roses.account.modular.consumer")
@EnableDiscoveryClient
@EnableBinding(OrderSink.class)
public class RosesAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesAccountApplication.class, args);
    }
}
