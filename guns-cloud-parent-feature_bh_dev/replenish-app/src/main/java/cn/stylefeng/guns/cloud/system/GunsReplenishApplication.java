package cn.stylefeng.guns.cloud.system;

import cn.stylefeng.guns.cloud.system.core.dbs.annotation.DataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 系统管理基础服务
 *
 * @author fengshuonan
 * @Date 2019/5/16 21:39
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.stylefeng.guns.cloud.system.modular.sys.consumer"})
public class GunsReplenishApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GunsReplenishApplication.class).run(args);
    }

}


