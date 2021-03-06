package cn.stylefeng.roses.message.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 消息服务
 * <p>
 * </p>
 *
 * @author stylefeng123
 * @since 2018-04-16
 */
@TableName("reliable_message")
@Data
public class ReliableMessage extends Model<ReliableMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 消息ID
     */
    @TableField("message_id")
    private String messageId;

    /**
     * 消息内容
     */
    @TableField("message_body")
    private String messageBody;

    /**
     * 消息数据类型
     */
    @TableField("message_data_type")
    private String messageDataType;

    /**
     * 消费队列
     */
    @TableField("topic")
    private String topic;

    /**
     * 消息重发次数
     */
    @TableField("message_send_times")
    private Integer messageSendTimes;

    /**
     * 是否死亡
     * <p>
     * Y：已死亡
     * N：未死亡
     */
    @TableField("already_dead")
    private String alreadyDead;

    /**
     * 状态
     * <p>
     * WAIT_VERIFY：待确认
     * SENDING：发送中
     */
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime = new Date();

    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 修改者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 版本号
     */
    @Version
    private Long version = 0L;

    /**
     * 版本号
     */
    @TableField("biz_unique_id")
    private Long bizUniqueId;

    public ReliableMessage() {

    }

    public ReliableMessage(String messageId, String messageBody, String topic) {
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.topic = topic;
    }

}
