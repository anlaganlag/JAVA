package cn.stylefeng.roses.account.core;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 用于接收分布式事务消息的通道
 * <p>
 * 一个业务一个通道
 *
 * @author fengshuonan
 * @Date 2020/5/7 1:44 下午
 */
public interface OrderSink {

    /**
     * Input channel name.
     */
    String INPUT = "order-input";

    /**
     * @return input channel.
     */
    @Input(OrderSink.INPUT)
    SubscribableChannel input();

}
