package cn.stylefeng.roses.account.modular.consumer;

import cn.stylefeng.roses.message.api.MessageServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 消息服务消费者
 *
 * @author fengshuonan
 * @date 2018-05-05 22:39
 */
@FeignClient("guns-cloud-tx-service")
public interface MessageServiceConsumer extends MessageServiceApi {
}
