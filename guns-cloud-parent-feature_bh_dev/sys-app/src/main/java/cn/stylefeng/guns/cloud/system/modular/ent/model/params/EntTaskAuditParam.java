package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 企业信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class EntTaskAuditParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 公司id
     */
    @ApiModelProperty("源仓任务名称")
    private String taskname;

    /**
     * 公司id
     */
    @ApiModelProperty("平台")
    private String platname;

    /**
     * 公司id
     */
    @ApiModelProperty("账号")
    private String shopname;

    /**
     * 公司id
     */
    @ApiModelProperty("站点")
    private String site;

    /**
     * 公司id
     */
    @ApiModelProperty("开始时间")
    private String startdate;

    /**
     * 公司id
     */
    @ApiModelProperty("结束时间")
    private String enddate;

    /**
     * 公司id
     */
    @ApiModelProperty("汇款日期")
    private Long depositdate;


    /**
     * 公司id
     */
    @ApiModelProperty("状态")
    private String auditstatus;


    /**
     * 公司id
     */
    @ApiModelProperty("状态")
    private String handlestatus;

    /**
     * 公司id
     */
    @ApiModelProperty("状态")
    private String banknum;



    @Override
    public String checkParam() {
        return null;
    }

}
