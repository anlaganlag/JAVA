package cn.stylefeng.roses.message.core.msg.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.roses.message.api.model.ReliableMessage;
import cn.stylefeng.roses.message.core.msg.MessageSender;
import cn.stylefeng.roses.message.core.msg.rocket.OrderSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * rocketmq发送消息
 *
 * @author fengshuonan
 * @Date 2020/5/6 3:53 下午
 */
@Service
public class RocketMqMessageSender implements MessageSender {

    @Resource
    private OrderSource txMsgSource;

    @Override
    public void sendMessage(ReliableMessage reliableMessage) {

        if (reliableMessage == null || ObjectUtil.isEmpty(reliableMessage.getTopic()) || ObjectUtil.isEmpty(reliableMessage.getMessageBody())) {
            throw new RequestEmptyException();
        }

        // 发送消息
        this.txMsgSource
                .output()
                .send(MessageBuilder.withPayload(reliableMessage.getMessageBody())
                        .setHeader("messageId", reliableMessage.getMessageId())
                        .build()
                );
    }

}
