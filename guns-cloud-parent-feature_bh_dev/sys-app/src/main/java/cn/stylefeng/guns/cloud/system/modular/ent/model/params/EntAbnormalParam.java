package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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
public class EntAbnormalParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 公司id
     */
    @ApiModelProperty("id")
    private int id;

    /**
     * 公司名称
     */
    @ApiModelProperty("平台")
    private String platname;

    /**
     * 公司名称
     */
    @ApiModelProperty("平台")
    private String shopname;

    /**
     * 公司名称
     */
    @ApiModelProperty("平台")
    private String site;
    /**
     * 公司名称
     */
    @ApiModelProperty("平台")
    private String startdate;

    /**
     * end_date
     */
    @ApiModelProperty("end_date")
    private String enddate;

    /**
     * deposit_date
     */
    @ApiModelProperty("deposit_date")
    private String depositdate;


    /**
     * currency
     */
    @ApiModelProperty("currency")
    private String currency;
    /**
     * status
     */
    @ApiModelProperty("status")
    private String status;

    /**
     * handle_status
     */
    @ApiModelProperty("handle_status")
    private String handlestatus;

    /**
     * remark
     */
    @ApiModelProperty("remark")
    private String remark;

    /**
     * create_time
     */
    @ApiModelProperty("create_time")
    private String createtime;

    /**
     * update_time
     */
    @ApiModelProperty("update_time")
    private String updateTime;

    /**
     * update_time
     */
    @ApiModelProperty("fileuploadpath")
    private String fileuploadpath;


    @Override
    public String checkParam() {
        return null;
    }

}
