package cn.stylefeng.roses.message.core.msg.rocket;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 用于接收特定分布式事务消息的
 * <p>
 * 每个单独的业务占用一个通道
 *
 * @author fengshuonan
 * @Date 2020/5/7 1:46 下午
 */
public interface OrderSource {

    /**
     * Name of the output channel.
     */
    String OUTPUT = "order-output";

    /**
     * @return output channel
     */
    @Output(OrderSource.OUTPUT)
    MessageChannel output();

}
