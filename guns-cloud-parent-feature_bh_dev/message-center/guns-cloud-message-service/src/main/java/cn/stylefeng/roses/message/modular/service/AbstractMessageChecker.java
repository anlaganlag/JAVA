package cn.stylefeng.roses.message.modular.service;

import cn.stylefeng.guns.cloud.model.page.PageQuery;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.roses.message.api.MessageServiceApi;
import cn.stylefeng.roses.message.api.model.ReliableMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象消息校验重发服务
 *
 * @author fengshuonan
 * @date 2018-05-08 23:04
 */
@Slf4j
public abstract class AbstractMessageChecker {

    @Autowired
    private MessageServiceApi messageServiceApi;

    public void checkMessages() {
        try {
            //存放查询结果
            Map<String, ReliableMessage> messageMap = new HashMap<>();

            int pageSize = 2000;            //每页条数
            int maxHandlePageCount = 3;     //一次最多处理页数

            int currentPage = 1;            //当前处理页
            PageQuery pageQuery = new PageQuery(pageSize, currentPage, "asc", "create_time");
            PageResult<ReliableMessage> pageResult = getPageResult(pageQuery);

            List<ReliableMessage> rows = pageResult.getRows();
            for (ReliableMessage item : rows) {
                messageMap.put(item.getMessageId(), item);
            }

            long totalPage = pageResult.getTotalPage();
            if (totalPage > maxHandlePageCount) {
                totalPage = maxHandlePageCount;
            }

            for (currentPage = 2; currentPage <= totalPage; currentPage++) {
                pageQuery = new PageQuery(pageSize, currentPage, "asc", "create_time");
                pageResult = getPageResult(pageQuery);
                if (pageResult != null && pageResult.getRows() != null) {
                    List<ReliableMessage> otherResults = pageResult.getRows();
                    for (ReliableMessage rowItem : otherResults) {
                        messageMap.put(rowItem.getMessageId(), rowItem);
                    }
                } else {
                    break;
                }
            }

            //开始处理
            processMessage(messageMap);

        } catch (Exception e) {
            log.error("处理待发送状态的消息异常！", e);
        }
    }

    protected abstract void processMessage(Map<String, ReliableMessage> messages);

    protected abstract PageResult<ReliableMessage> getPageResult(PageQuery pageQuery);
}
