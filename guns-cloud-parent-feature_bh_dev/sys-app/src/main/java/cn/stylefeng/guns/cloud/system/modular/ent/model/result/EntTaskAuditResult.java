package cn.stylefeng.guns.cloud.system.modular.ent.model.result;

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
public class EntTaskAuditResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 公司id
     */
    @ApiModelProperty("id")
    private String id;

    /**
     * report_id
     */
    @ApiModelProperty("reportId")
    private String reportId;

    /**
     * taskname
     */
    @ApiModelProperty("taskname")
    private String taskname;

    /**
     * dwplatname
     */
    @ApiModelProperty("platname")
    private String platname;

    /**
     * shopname
     */
    @ApiModelProperty("shopname")
    private String shopname;

    /**
     * site
     */
    @ApiModelProperty("site")
    private String site;

    /**
     * start_date
     */
    @ApiModelProperty("start_date")
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
     * amount
     */
    @ApiModelProperty("amount")
    private String amount;

    /**
     * total_amount
     */
    @ApiModelProperty("total_amount")
    private String totalamount;

    /**
     * audit_status
     */
    @ApiModelProperty("audit_status")
    private String auditstatus;

    /**
     * handle_status
     */
    @ApiModelProperty("handle_status")
    private String handlestatus;

    /**
     * bank_num
     */
    @ApiModelProperty("bank_num")
    private String banknum;

    /**
     * currency
     */
    @ApiModelProperty("currency")
    private String currency;

    /**
     * personname
     */
    @ApiModelProperty("personname")
    private String personname;

    /**
     * personname
     */
    @ApiModelProperty("remark")
    private String remark;

    /**
     * create_time
     */
    @ApiModelProperty("createTime")
    private String createTime;

    /**
     * create_date
     */
    @ApiModelProperty("createDate")
    private String createDate;

    /**
     * update_time
     */
    @ApiModelProperty("updateTime")
    private String updateTime;
}
