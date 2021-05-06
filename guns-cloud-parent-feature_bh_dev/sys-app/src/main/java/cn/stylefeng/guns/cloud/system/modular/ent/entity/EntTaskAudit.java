package cn.stylefeng.guns.cloud.system.modular.ent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * settlement报告审核
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@TableName("TbSettlementTaskAudit")
public class EntTaskAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    @TableField("id")
    private String id;

    /**
     * report_id
     */
    @TableField("report_id")
    private String reportId;

    /**
     * taskname
     */
    @TableField("taskname")
    private String taskname;

    /**
     * platname
     */
    @TableField("platname")
    private String platname;

    /**
     * shopname
     */
    @TableField("shopname")
    private String shopname;

    /**
     * site
     */
    @TableField("site")
    private String site;

    /**
     * start_date
     */
    @TableField("start_date")
    private String startdate;

    /**
     * end_date
     */
    @TableField("end_date")
    private String enddate;

    /**
     * deposit_date
     */
    @TableField("deposit_date")
    private String depositdate;

    /**
     * amount
     */
    @TableField("amount")
    private String amount;

    /**
     * total_amount
     */
    @TableField("total_amount")
    private String totalamount;

    /**
     * audit_status
     */
    @TableField("audit_status")
    private String auditstatus;

    /**
     * personname
     */
    @TableField("personname")
    private String personname;

    /**
     * bank_num
     */
    @TableField("bank_num")
    private String banknum;

    /**
     * currency
     */
    @TableField("currency")
    private String currency;

    /**
     * handle_status
     */
    @TableField("handle_status")
    private String handlestatus;

    /**
     * remark
     */
    @TableField("remark")
    private String remark;

    /**
     * create_time
     */
    @TableField("create_time")
    private String createtime;

    /**
     * update_time
     */
    @TableField("update_time")
    private String updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getPlatname() {
        return platname;
    }

    public void setPlatname(String platname) {
        this.platname = platname;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getHandlestatus() {
        return handlestatus;
    }

    public void setHandlestatus(String handlestatus) {
        this.handlestatus = handlestatus;
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String site) {
        this.amount = amount;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(String depositdate) {
        this.depositdate = depositdate;
    }



    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
