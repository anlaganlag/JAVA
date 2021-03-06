package cn.stylefeng.roses.message.config.properteis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 消息服务的配置
 *
 * @author fengshuonan
 * @date 2018-05-10 22:55
 */
@Component
@ConfigurationProperties(prefix = "roses.message")
public class MessageProperties {

    /**
     * 消息检查的时间段
     */
    private Integer checkIntervalSeconds;

    public Integer getCheckIntervalSeconds() {
        return checkIntervalSeconds;
    }

    public void setCheckIntervalSeconds(Integer checkIntervalSeconds) {
        this.checkIntervalSeconds = checkIntervalSeconds;
    }
}
