package cn.stylefeng.roses.message;

import cn.stylefeng.roses.message.core.msg.rocket.OrderSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 消息服务子系统
 *
 * @author stylefeng
 * @Date 2018/1/22 21:27
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "cn.stylefeng.roses.message.modular.consumer")
@EnableDiscoveryClient
@EnableScheduling
@EnableBinding({OrderSource.class})
public class RosesMessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesMessageServiceApplication.class, args);
    }

}
