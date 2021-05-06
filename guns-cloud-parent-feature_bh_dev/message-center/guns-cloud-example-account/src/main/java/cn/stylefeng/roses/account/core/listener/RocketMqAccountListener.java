package cn.stylefeng.roses.account.core.listener;

import cn.stylefeng.roses.account.core.OrderSink;
import cn.stylefeng.roses.account.modular.service.IFlowRecordService;
import cn.stylefeng.roses.message.api.order.GoodsFlowParam;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * rocketmq的监听
 *
 * @author fengshuonan
 * @Date 2019/9/4 22:24
 */
@Service
public class RocketMqAccountListener {

    @Autowired
    private IFlowRecordService flowRecordService;

    @StreamListener(value = OrderSink.INPUT, condition = "headers['my-header']=='你的header'")
    public void receive(String messageBody) {
        GoodsFlowParam goodsFlowParam = JSON.parseObject(messageBody, GoodsFlowParam.class);
        flowRecordService.recordFlow(goodsFlowParam);
    }

}