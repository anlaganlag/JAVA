package cn.stylefeng.roses.message.modular.controller;

import cn.stylefeng.guns.cloud.model.web.response.SuccessResponseData;
import cn.stylefeng.roses.message.api.enums.MessageQueueEnum;
import cn.stylefeng.roses.message.api.model.ReliableMessage;
import cn.stylefeng.roses.message.core.msg.MessageSender;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试队列
 *
 * @author fengshuonan
 * @date 2020-05-07-1:56 下午
 */
@RestController
public class TestController {

    @Resource
    private MessageSender messageSender;

    /**
     * 测试队列
     *
     * @author fengshuonan
     * @Date 2020/5/7 1:57 下午
     */
    @RequestMapping("/testmq")
    @ResponseBody
    public SuccessResponseData test() {

        String messageId = IdWorker.getIdStr();
        String queue = MessageQueueEnum.MAKE_ORDER.name();
        ReliableMessage reliableMessage = new ReliableMessage(messageId, "aaaaaaaaa", queue);
        reliableMessage.setBizUniqueId(123L);

        messageSender.sendMessage(reliableMessage);
        return new SuccessResponseData();
    }

}
