package cn.stylefeng.roses.message.modular.service.impl;

import cn.stylefeng.guns.cloud.model.page.PageQuery;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.roses.message.api.MessageServiceApi;
import cn.stylefeng.roses.message.api.model.ReliableMessage;
import cn.stylefeng.roses.message.modular.service.AbstractMessageChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Map;

/**
 * 处理状态为“发送中”但超时没有被成功消费确认的消息
 *
 * @author fengshuonan
 * @date 2018-05-08 23:07
 */
@Service
public class SendingMessageChecker extends AbstractMessageChecker {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageServiceApi messageServiceApi;

    @Resource(name = "sendTimeInterval")
    private Map<Integer, Integer> notifyParam;

    @Override
    protected void processMessage(Map<String, ReliableMessage> messageMap) {

        // 单条消息处理
        for (Map.Entry<String, ReliableMessage> entry : messageMap.entrySet()) {
            ReliableMessage message = entry.getValue();
            try {
                // 判断发送次数
                int maxTimes = notifyParam.size();

                // 如果超过最大发送次数直接退出
                if (message.getMessageSendTimes() > maxTimes) {

                    // 标记为死亡
                    messageServiceApi.setMessageToAreadlyDead(message.getMessageId());
                    continue;
                }

                // 获取消息已经从发的次数
                int reSendTimes = message.getMessageSendTimes();

                // 获取再次发送的间隔分钟数，每次从发间隔不一样
                int reSendIntervalMinutes = notifyParam.get(reSendTimes == 0 ? 1 : reSendTimes);

                // 获取当前时间
                long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();

                // 获取推算的下次发送的时间
                long nextExecuteTime = message.getUpdateTime().getTime() + reSendIntervalMinutes * 60 * 1000;

                // 若当前时间超过推算的发送时间，则重新发送消息
                if (currentTimeInMillis >= nextExecuteTime) {
                    messageServiceApi.reSendMessage(message);
                }

            } catch (Exception e) {
                logger.error("处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
            }
        }

    }

    @Override
    protected PageResult<ReliableMessage> getPageResult(PageQuery pageQuery) {
        return messageServiceApi.listPageSendingTimeOutMessages(pageQuery);
    }

}
