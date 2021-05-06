package cn.stylefeng.guns.cloud.system.config;

import cn.stylefeng.guns.cloud.libs.config.properties.FtpProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ftp的配置
 *
 * @author fengshuonan
 * @date 2021/4/29 22:02
 */
@Configuration
public class FtpConfig {

    @Bean
    @ConfigurationProperties(prefix = "ftp")
    public FtpProperties ftpProperties() {
        return new FtpProperties();
    }


}
